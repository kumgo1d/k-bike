package com.goldcompany.apps.koreabike.ui.search_address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress

class SearchAddressAdapter(
    private val viewModel: SearchAddressViewModel
): PagingDataAdapter<ApiAddress, SearchAddressAdapter.ViewHolder>(SearchAddressDiffCallback()) {

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
        fun bind(item: ApiAddress) {
            binding.address = item

            itemView.setOnClickListener {
                val userAddress = UserHistoryAddress(
                    latitude = item.y.toDouble(),
                    longitude = item.x.toDouble(),
                    address = item.addressName,
                    keyword = item.placeName,
                    selected = true
                )

                viewModel.setCurrentAddress(userAddress)
                Navigation.findNavController(itemView).popBackStack()
            }
        }
    }
}

private class SearchAddressDiffCallback : DiffUtil.ItemCallback<ApiAddress>() {
    override fun areItemsTheSame(
        oldItem: ApiAddress,
        newItem: ApiAddress
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ApiAddress,
        newItem: ApiAddress
    ): Boolean {
        return oldItem == newItem
    }
}