package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.calculate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.Categories
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.databinding.CategoryTypeBinding


class CategoriesAdapter(
    private val onItemClicked: (position: Int, itemAtPosition: Categories, binding: CategoryTypeBinding) -> Unit
) : ListAdapter<Categories, CategoriesAdapter.CategoryVH>(object :
    DiffUtil.ItemCallback<Categories>() {

    override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
        return oldItem.itemName == newItem.itemName
    }

    override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        //inflate the view to be used by the payment option view holder
        val binding =
            CategoryTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryVH(binding, onItemClick = { position ->
            val itemAtPosition = currentList[position]
            this.onItemClicked(position, itemAtPosition, binding)
        })

    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val itemAtPosition = currentList[position]
        holder.bind(itemAtPosition)
    }


    inner class CategoryVH(
        private val binding: CategoryTypeBinding,
        onItemClick: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }


        fun bind(transaction: Categories) {

            with(binding) {
                itemName.text = transaction.itemName
            }
        }

    }
}