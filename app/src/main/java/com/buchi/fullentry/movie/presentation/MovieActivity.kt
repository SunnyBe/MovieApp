package com.buchi.fullentry.movie.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.buchi.fullentry.databinding.ActivityMovieBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.error.mapLatest { msg ->
            msg?.let {
                runOnUiThread {
                    Snackbar.make(binding.root, "Error: \n$it", Snackbar.LENGTH_LONG).show()
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.loading.mapLatest {
            runOnUiThread {
                if (it) Toast.makeText(this@MovieActivity, "Updating list", Toast.LENGTH_LONG).show()
            }
        }.launchIn(lifecycleScope)

    }
}