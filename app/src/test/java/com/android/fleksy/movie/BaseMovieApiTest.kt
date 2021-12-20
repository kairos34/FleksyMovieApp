package com.android.fleksy.movie

import com.android.fleksy.movie.data.MockResponseFileReader
import com.android.fleksy.movie.data.remote.MovieApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseMovieApiTest {

    abstract val responseJsonPath: String

    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000
    private val mockResponseFileReader = MockResponseFileReader()
    private lateinit var okHttpClient: OkHttpClient
    lateinit var movieApi: MovieApi

    @Before
    fun setup() {
        server.start(MOCK_WEBSERVER_PORT)

        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()

        movieApi = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)

        server.enqueue(
            MockResponse()
            .throttleBody(1024, 1, TimeUnit.SECONDS)
            .setResponseCode(200)
            .setBody(mockResponseFileReader(responseJsonPath))
        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}