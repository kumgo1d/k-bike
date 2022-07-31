package com.goldcompany.apps.koreabike.ui.search_address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding
import com.goldcompany.koreabike.domain.model.Address

class SearchAddressAdapter(
    private val viewModel: SearchAddressViewModel
): PagingDataAdapter<Address, SearchAddressAdapter.ViewHolder>(SearchAddressDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchAddressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemSearchAddressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Address) {
            binding.address = item

            itemView.setOnClickListener {
                viewModel.setCurrentAddress(item)
                Navigation.findNavController(itemView).popBackStack()
            }
        }
    }
}

private class SearchAddressDiffCallback : DiffUtil.ItemCallback<Address>() {
    override fun areItemsTheSame(
        oldItem: Address,
        newItem: Address
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Address,
        newItem: Address
    ): Boolean {
        return oldItem == newItem
    }
}