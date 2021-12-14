package com.android.fleksy.movie.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.fleksy.movie.common.Resource
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.domain.usecases.GetMoviesUseCase
import com.android.fleksy.movie.domain.usecases.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : ViewModel() {

    private lateinit var randomMovie: Movie

    fun getMovies() {
        getMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.forEach {
                        Log.d(javaClass.simpleName, it.toString())
                    }
                    randomMovie = result.data?.get((0 until result.data.size - 1).random())!!
                }
                is Resource.Error -> {
                    Log.e(javaClass.simpleName, result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    Log.d(javaClass.simpleName, "Loading movies...")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getSimilarMovies() {
        getSimilarMoviesUseCase(randomMovie.id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.forEach {
                        Log.d(javaClass.simpleName, it.toString())
                    }
                }
                is Resource.Error -> {
                    Log.e(javaClass.simpleName, result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    Log.d(javaClass.simpleName, "Loading similar movies for $randomMovie...")
                }
            }
        }.launchIn(viewModelScope)
    }
}