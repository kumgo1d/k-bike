package com.goldcompany.apps.koreabike.ui.search_address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.databinding.SubSearchAddressItemBinding
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress

class SearchAddressAdapter(
    private val viewModel: SearchAddressViewModel
): ListAdapter<AddressItem, SearchAddressAdapter.ViewHolder>(SearchAddressDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SubSearchAddressItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: SubSearchAddressItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val keyword = binding.itemKeyword
        val address = binding.itemAddress

        fun bind(item: AddressItem) {
            keyword.text = item.placeName
            address.text = item.addressName

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

private class SearchAddressDiffCallback : DiffUtil.ItemCallback<AddressItem>() {
    override fun areItemsTheSame(
        oldItem: AddressItem,
        newItem: AddressItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AddressItem,
        newItem: AddressItem
    ): Boolean {
        return oldItem == newItem
    }
}