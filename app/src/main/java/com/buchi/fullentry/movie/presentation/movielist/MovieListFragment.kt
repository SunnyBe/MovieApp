package com.buchi.fullentry.movie.presentation.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var binding: FragmentMovieListBinding
    private val viewModel: MovieListViewModel by viewModels()
    private val activityViewModel: MovieViewModel by activityViewModels()

    private val movieListListener = object : MovieListAdapter.MovieListListener {
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
        binding.movieList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(viewModel.listId))

        binding.searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null && query.isNotBlank()) {
                    viewModel.setStateEvent(MovieListStateEvents.QueryList(query))
                    true
                } else {
                    false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText != null && newText.isNotBlank()) {
                    viewModel.setStateEvent(MovieListStateEvents.QueryList(newText))
                    true
                } else {
                    false
                }
            }
        })

        binding.searchQuery.setOnCloseListener {
            Log.d(javaClass.simpleName, "SearchView cancelled")
            viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(viewModel.listId))
            true
        }
        binding.searchQuery.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                Log.d(javaClass.simpleName, "SearchView cancelled2")
                return true
            }
        })

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
                    // Only update the current list if there's a new list
                    if (movies.isNotEmpty()) updateRecyclerView(movies)
                }
            }
        }
    }

    private fun updateRecyclerView(movies: List<Movie>) {
        listAdapter.submitList(movies)
    }

    private fun navigateToDetailPage(item: Movie) {
        val bundle = Bundle().apply {
            putParcelable("movie", item)
        }
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)
    }

    override fun onRefresh() {
        viewModel.setStateEvent(MovieListStateEvents.FetchMovieList(viewModel.listId))
    }
}