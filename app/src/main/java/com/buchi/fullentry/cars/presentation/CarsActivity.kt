package com.buchi.fullentry.cars.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.buchi.fullentry.R
import com.buchi.fullentry.databinding.ActivityCarBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CarsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarBinding
    private val viewModel: CarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.error.mapLatest { msg ->
            msg?.let {
                runOnUiThread {
                    Snackbar.make(binding.root, "Error: \n$it", Snackbar.LENGTH_LONG)
                        .setAnchorView(binding.bottomNavigationView)
                        .show()
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.loading.mapLatest {
            runOnUiThread {
                if (it) Toast.makeText(this@CarsActivity, "Updating list", Toast.LENGTH_LONG).show()
            }
        }.launchIn(lifecycleScope)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_navigation -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.favourite_navigation -> {
                    // Respond to navigation item 2 click
                    true
                }

                R.id.cars_navigation -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.notification_navigation -> {
                    // Respond to navigation item 2 click
                    true
                }

                R.id.messenger_navigation -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.home_navigation -> {

                }
            }
        }

    }
}