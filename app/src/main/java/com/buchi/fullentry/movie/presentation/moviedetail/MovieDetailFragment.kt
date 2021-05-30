package com.buchi.fullentry.movie.presentation.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.buchi.fullentry.R
import com.buchi.fullentry.databinding.FragmentMovieDetailBinding
import com.buchi.fullentry.movie.model.Movie
import com.buchi.fullentry.movie.presentation.MovieViewModel
import com.buchi.fullentry.utilities.Constants
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    lateinit var binding: FragmentMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private val activityViewModel: MovieViewModel by activityViewModels()

    private var movieDetail: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            movieDetail = getParcelable(Constants.MOVIE_ITEM)
            if (movieDetail != null) {
                populateDetailView(movieDetail)
            } else {
                findNavController().navigateUp()
            }
        }

        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

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
                vs.movieDetail?.let { movies ->
                    Log.d(javaClass.simpleName, movies.toString())
                }
            }
        }
    }

    private fun populateDetailView(movie: Movie?) {
        with(binding) {
            toolBar.setTitleTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            toolBar.title = movie?.title ?: "Detail"
            movieDescr.text = movie?.overview
            with(detailInclude) {
                langDetail.text = resources.getString(
                    R.string.detail_value_string,
                    "Language",
                    movie?.originalLanguage?.toUpperCase()
                )
                voteAverage.text = resources.getString(
                    R.string.detail_value_string,
                    "Popularity",
                    movie?.popularity.toString()
                )
                releaseDate.text = resources.getString(
                    R.string.detail_value_string,
                    "Release Date",
                    movie?.releaseDate
                )
            }

            Glide.with(requireContext())
                .load(Constants.IMAGE_BASE_URL + movie?.posterPath)
                .placeholder(R.drawable.baseline_photo_black_24dp)
                .error(R.drawable.baseline_broken_image_pink_500_24dp)
                .centerCrop()
                .fitCenter()
                .into(movieImage)
        }
    }
}