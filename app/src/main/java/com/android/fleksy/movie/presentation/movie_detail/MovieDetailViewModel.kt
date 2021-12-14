package com.android.fleksy.movie.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.common.Resource
import com.android.fleksy.movie.domain.usecases.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_TV_ID)?.let { tvId ->
            getSimilarMovies(tvId)
        }
    }

    private fun getSimilarMovies(tvId: String) {
        getSimilarMoviesUseCase(tvId.toInt()).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieDetailState(similarMovies = result.data ?: listOf())
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}