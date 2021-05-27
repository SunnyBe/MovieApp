package com.buchi.fullentry.movie.presentation.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.buchi.fullentry.databinding.FragmentMovieListBinding
import com.buchi.fullentry.movie.adapter.MovieListAdapter
import com.buchi.fullentry.movie.model.Movie
import com.buchi.fullentry.movie.presentation.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieListFragment : Fragment() {
    lateinit var binding: FragmentMovieListBinding
    private val viewModel: MovieListViewModel by viewModels()
    private val activityViewModel: MovieViewModel by viewModels()

    private val listAdapter: MovieListAdapter by lazy {
        MovieListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(1))

        binding.searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    listAdapter.submitList(listAdapter.currentList.filter { it.title == query })
                    return true
                } ?: kotlin.run {
                    return false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    listAdapter.currentList.filter { it.title == newText }
                    return true
                } ?: kotlin.run {
                    return false
                }
            }
        })

        lifecycleScope.launchWhenStarted {
            viewModel.dataState.mapLatest { ds ->
                activityViewModel.dataStateChanged(ds)

                ds.data?.let { event ->
                    event.getContentIfNotHandled()?.let { loginViewState ->
                        viewModel.processViewState(loginViewState)
                    }
                }
            }.launchIn(lifecycleScope)

            viewModel.viewState.collectLatest { vs ->
                vs.movieList?.let { movies ->
                    Log.d(javaClass.simpleName, movies.toString())
                    updateRecyclerView(movies)
                }
            }
        }
    }

    private fun updateRecyclerView(movies: List<Movie>) {
        listAdapter.submitList(movies)
        binding.movieList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}