package com.buchi.fullentry.cars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buchi.fullentry.cars.model.InspectionItems
import com.buchi.fullentry.databinding.InspectItemBinding

class ConditionsAdapter() :
    ListAdapter<InspectionItems, ConditionsAdapter.ConditionsViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<InspectionItems>() {
            override fun areItemsTheSame(
                oldItem: InspectionItems,
                newItem: InspectionItems
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: InspectionItems,
                newItem: InspectionItems
            ): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    class ConditionsViewHolder(
        private val carItemBinding: InspectItemBinding
    ) :
        RecyclerView.ViewHolder(carItemBinding.root) {

        fun bind(item: InspectionItems) {
            carItemBinding.conditionName.text = item.name
            carItemBinding.conditionComment.text = item.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConditionsViewHolder {
        val itemBinding =
            InspectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConditionsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ConditionsViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }
}