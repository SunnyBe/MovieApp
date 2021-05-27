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

class MovieListAdapter: ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class MovieListViewHolder(private val movieItemBinding: MovieItemBinding): RecyclerView.ViewHolder(movieItemBinding.root) {

        fun bind(item: Movie) {
            movieItemBinding.movieTitle.text = item.title
            movieItemBinding.movieDescr.text = item.overview

            Glide.with(movieItemBinding.root.context)
                .load(item.posterPath)
                .placeholder(R.drawable.baseline_photo_black_24dp)
                .error(R.drawable.baseline_broken_image_pink_500_24dp)
                .centerCrop()
                .fitCenter()
                .into(movieItemBinding.movieIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val movieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}