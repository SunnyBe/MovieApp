package com.buchi.fullentry.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buchi.fullentry.R
import com.buchi.fullentry.databinding.MovieItemBinding
import com.buchi.fullentry.movie.model.Movie
import com.bumptech.glide.Glide

class MovieListAdapter(private val movieListListener: MovieListListener) :
    ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class MovieListViewHolder(private val movieItemBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(movieItemBinding.root) {

        fun bind(item: Movie, movieListListener: MovieListListener) {
            movieItemBinding.movieTitle.text = item.title
            movieItemBinding.movieDescr.text = item.overview
//            https://image.tmdb.org/t/p/w500/c24sv2weTHPsmDa7jEMN0m2P3RT.jpg
            Glide.with(movieItemBinding.root.context)
                .load("https://image.tmdb.org/t/p/w500/" + item.posterPath)
                .placeholder(R.drawable.baseline_photo_black_24dp)
                .error(R.drawable.baseline_broken_image_pink_500_24dp)
                .centerCrop()
                .fitCenter()
                .into(movieItemBinding.movieIcon)
            movieItemBinding.root.setOnClickListener {
                movieListListener.onItemClicked(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val movieItemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, movieListListener)
    }

    interface MovieListListener {
        fun onItemClicked(item: Movie)
    }
}