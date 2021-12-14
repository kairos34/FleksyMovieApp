package com.android.fleksy.movie.di

import com.android.fleksy.movie.common.Constants
import com.android.fleksy.movie.data.remote.MovieApi
import com.android.fleksy.movie.data.repository.MovieRepositoryImpl
import com.android.fleksy.movie.domain.repository.MovieRepository
import com.android.fleksy.movie.util.apiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain -> chain.apiKeyInterceptor() }
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_MOVIE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}