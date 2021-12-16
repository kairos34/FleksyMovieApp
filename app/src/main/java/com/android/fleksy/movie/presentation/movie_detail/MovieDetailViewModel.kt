package com.android.fleksy.movie.presentation.movie_detail

import android.graphics.drawable.BitmapDrawable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.ImagePainter
import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.common.Resource
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.domain.usecases.GetMovieUseCase
import com.android.fleksy.movie.domain.usecases.GetSimilarMoviesUseCase
import com.android.fleksy.movie.presentation.theme.ColorPrimary
import com.android.fleksy.movie.presentation.theme.DarkGray
import com.android.fleksy.movie.util.generateDominantColorState
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

    private val _movie = mutableStateOf(Movie())
    val movie: MutableState<Movie> = _movie

    private val _index = mutableStateOf(0)
    val index: MutableState<Int> = _index

    private val _brush = mutableStateOf(
        Brush.verticalGradient(
            colors = listOf(ColorPrimary, DarkGray)
        )
    )
    val brush: MutableState<Brush> = _brush

    init {
        refresh()
    }

    fun refresh() {
        savedStateHandle.get<String>(Constants.PARAM_TV_ID)?.let { tvId ->
            getSimilarMovies(tvId.toInt())
        }
    }

    fun updateBrushByCurrentMovieColor(painter: ImagePainter) {
        _movie.value.posterPath?.run {
            viewModelScope.launch {
                val image =
                    (painter.imageLoader.execute(painter.request).drawable as BitmapDrawable).bitmap
                val swatch = image.generateDominantColorState()
                _brush.value = Brush.verticalGradient(
                    listOf(Color(swatch.rgb), DarkGray)
                )
            }
        }
    }

    fun updateCurrentMovie() {
        _state.value.similarMovies?.run {
            _movie.value = this[_index.value]
        }
    }

    private fun getSimilarMovies(tvId: Int) {
        getSimilarMoviesUseCase(tvId).onEach { similarMoviesResult ->
            when (similarMoviesResult) {
                is Resource.Success -> {
                    getMovieUseCase(tvId).onEach { movieResult ->
                        when (movieResult) {
                            is Resource.Success -> {
                                _movie.value = movieResult.data!!
                                _state.value = MovieDetailState(
                                    similarMovies = (
                                            listOf(movieResult.data) +
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