package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.SearchDeliveries
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.databinding.SearchItemsBinding


class SearchTransactionAdapter(
    private val onItemClicked: (position: Int, itemAtPosition: SearchDeliveries) -> Unit
) : ListAdapter<SearchDeliveries, SearchTransactionAdapter.TransactionHistoryVH>(object :
    DiffUtil.ItemCallback<SearchDeliveries>() {

    override fun areItemsTheSame(oldItem: SearchDeliveries, newItem: SearchDeliveries): Boolean {
        return oldItem.status == newItem.status
    }

    override fun areContentsTheSame(oldItem: SearchDeliveries, newItem: SearchDeliveries): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHistoryVH {
        //inflate the view to be used by the payment option view holder
        val binding = SearchItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionHistoryVH(binding, onItemClick = { position ->
            val itemAtPosition = currentList[position]
            this.onItemClicked(position, itemAtPosition)
        })

    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: TransactionHistoryVH, position: Int) {
        val itemAtPosition = currentList[position]
        holder.bind(itemAtPosition)
    }


    inner class TransactionHistoryVH(
        private val binding: SearchItemsBinding,
        onItemClick: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }


        fun bind(transaction: SearchDeliveries) {

            with(binding) {
                senderLabel.text = transaction.itemName
                receiptNum.text = transaction.receipt_number
                fromRoute.text = transaction.from_route
                toRoute.text = transaction.to_route
            }
        }

    }
}