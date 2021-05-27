package com.buchi.fullentry.movie.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.buchi.fullentry.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.error.collectLatest {
                Log.d(javaClass.simpleName, "Error state: $it")
            }

            viewModel.loading.collectLatest {
                Log.d(javaClass.simpleName, "Loading state: $it")
            }
        }

    }
}