package com.buchi.fullentry.cars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buchi.fullentry.R
import com.buchi.fullentry.cars.model.Car
import com.buchi.fullentry.databinding.CarItemBinding
import com.buchi.fullentry.utilities.*
import com.bumptech.glide.Glide

class CarListAdapter(private val carListListener: CarListListener) :
    ListAdapter<Car, CarListAdapter.ListViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Car>() {
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class ListViewHolder(private val carItemBinding: CarItemBinding) :
        RecyclerView.ViewHolder(carItemBinding.root) {

        fun bind(item: Car, carListListener: CarListListener) {
            carItemBinding.carTitle.text = item.title
            carItemBinding.carMake.text = item.city
            carItemBinding.carPrice.text = item.marketplacePrice?.toDouble()?.formatAmount
            carItemBinding.gradeScore.text = item.gradeScore?.to1dp?.inBrace
            Glide.with(carItemBinding.root.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.baseline_photo_black_24dp)
                .error(R.drawable.baseline_broken_image_pink_500_24dp)
                .centerCrop()
                .fitCenter()
                .into(carItemBinding.carIcon)
            carItemBinding.root.setOnClickListener {
                carListListener.onItemClicked(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val movieItemBinding =
            CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val car = getItem(position)
        holder.bind(car, carListListener)
    }

    interface CarListListener {
        fun onItemClicked(item: Car)
    }
}