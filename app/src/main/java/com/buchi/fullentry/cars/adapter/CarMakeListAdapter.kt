package com.buchi.fullentry.cars.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buchi.fullentry.R
import com.buchi.fullentry.cars.model.CarMake
import com.buchi.fullentry.databinding.CarMakeItemBinding
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


class CarMakeListAdapter(
    private val activity: Activity,
    private val carListListener: CarMakeListListener
) :
    ListAdapter<CarMake, CarMakeListAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CarMake>() {
            override fun areItemsTheSame(oldItem: CarMake, newItem: CarMake): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CarMake, newItem: CarMake): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class ListViewHolder(
        private val activity: Activity,
        private val carItemBinding: CarMakeItemBinding
    ) :
        RecyclerView.ViewHolder(carItemBinding.root) {

        fun bind(item: CarMake, carListListener: CarMakeListListener) {
            carItemBinding.makeTitle.text = item.name
            item.imageUrl?.let {
                if (item.imageUrl.contains("svg", true)) {
                    val uri: Uri = Uri.parse(item.imageUrl)
                    loadImageWithSvg(activity = activity, imageUri = uri, carItemBinding.makeIcon)
                } else {
                    Glide.with(carItemBinding.root.context)
                        .load(item.imageUrl)
                        .placeholder(R.drawable.baseline_photo_black_24dp)
                        .error(R.drawable.baseline_broken_image_pink_500_24dp)
                        .centerCrop()
                        .fitCenter()
                        .into(carItemBinding.makeIcon)
                }
            }

            carItemBinding.root.setOnClickListener {
                carListListener.onItemClicked(item)
            }
        }

        private fun loadImageWithSvg(activity: Activity, imageUri: Uri, imageView: ImageView) {
            GlideToVectorYou.justLoadImage(activity, imageUri, imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding =
            CarMakeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(activity, itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val car = getItem(position)
        holder.bind(car, carListListener)
    }

    interface CarMakeListListener {
        fun onItemClicked(item: CarMake)
    }
}