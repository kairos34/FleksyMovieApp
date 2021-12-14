package com.android.fleksy.movie.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.fleksy.movie.domain.model.Movie
import com.android.fleksy.movie.domain.usecases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    fun getMovies(): Flow<PagingData<Movie>> = Pager(
        PagingConfig(pageSize = 20),
        pagingSourceFactory = { getMoviesUseCase }).flow.cachedIn(viewModelScope)
}