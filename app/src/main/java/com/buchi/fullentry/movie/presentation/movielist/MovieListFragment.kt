package com.buchi.fullentry.movie.presentation.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.buchi.fullentry.R
import com.buchi.fullentry.databinding.FragmentMovieListBinding
import com.buchi.fullentry.movie.adapter.MovieListAdapter
import com.buchi.fullentry.movie.model.Movie
import com.buchi.fullentry.movie.presentation.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var binding: FragmentMovieListBinding
    private val viewModel: MovieListViewModel by viewModels()
    private val activityViewModel: MovieViewModel by activityViewModels()

    private val movieListListener = object: MovieListAdapter.MovieListListener{
        override fun onItemClicked(item: Movie) {
            navigateToDetailPage(item)
        }

    }

    private val listAdapter: MovieListAdapter by lazy {
        MovieListAdapter(movieListListener)
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
        binding.movieRefreshAction.setOnRefreshListener(this)
        viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(1))

        binding.searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setStateEvent(MovieListStateEvents.QueryList(query))
                    return true
                } ?: kotlin.run {
                    return false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.setStateEvent(MovieListStateEvents.QueryList(newText))
                    return true
                } ?: kotlin.run {
                    return false
                }
            }
        })

        binding.searchQuery.setOnCloseListener {
            viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(1))
            true
        }

        lifecycleScope.launchWhenStarted {
            viewModel.dataState.mapLatest { ds ->
                binding.movieRefreshAction.isRefreshing = ds.loading
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
                    binding.movieRefreshAction.isRefreshing = false
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
    private fun navigateToDetailPage(item: Movie) {
        val bundle = Bundle().apply {
            putParcelable("movie", item)
        }
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)
    }

    override fun onRefresh() {
        Log.d(javaClass.simpleName, "is onRefresh called()")
        viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(5))
    }
}