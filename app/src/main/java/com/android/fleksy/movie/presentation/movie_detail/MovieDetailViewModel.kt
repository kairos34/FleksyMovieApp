package com.android.fleksy.movie.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.common.Resource
import com.android.fleksy.movie.domain.usecases.GetMovieUseCase
import com.android.fleksy.movie.domain.usecases.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        refresh()
    }

    fun refresh() {
        savedStateHandle.get<String>(Constants.PARAM_TV_ID)?.let { tvId ->
            getSimilarMovies(tvId.toInt())
        }
    }

    private fun getSimilarMovies(tvId: Int) {
        getSimilarMoviesUseCase(tvId).onEach { similarMoviesResult ->
            when (similarMoviesResult) {
                is Resource.Success -> {
                    getMovieUseCase(tvId).onEach { movieResult ->
                        when (movieResult) {
                            is Resource.Success -> {
                                _state.value =
                                    MovieDetailState(
                                        similarMovies = (
                                                listOf(movieResult.data!!) +
                                                        // Sort similar movies by rating
                                                        (similarMoviesResult.data?.sortedByDescending { it.vote }
                                                            ?: listOf()
                                                                )
                                                ).distinctBy {
                                                // Eliminate same movies
                                                it.id
                                            }
                                    )
                            }
                            is Resource.Error -> {
                                _state.value = MovieDetailState(
                                    error = movieResult.message ?: "An unexpected error occured"
                                )
                            }
                            is Resource.Loading -> {
                                _state.value = MovieDetailState(isLoading = true)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(
                        error = similarMoviesResult.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}