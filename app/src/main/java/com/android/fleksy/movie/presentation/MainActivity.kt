package com.android.fleksy.movie.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.fleksy.movie.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenCreated {
            viewModel.getMovies()
            delay(2000)
            viewModel.getSimilarMovies()
        }
    }
}