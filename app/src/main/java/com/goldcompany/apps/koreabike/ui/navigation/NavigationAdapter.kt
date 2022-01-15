package com.goldcompany.apps.koreabike.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding
import com.goldcompany.apps.koreabike.util.ViewHelper

class NavigationAdapter(
    private val viewModel: NavigationViewModel
    ): PagingDataAdapter<AddressItem, NavigationAdapter.ViewHolder>(NavigationAddressDiffCallback()) {

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
        getItem(position)?.let { holder.bind(it, viewModel) }
    }

    class ViewHolder(private val binding: ItemSearchAddressBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddressItem, viewModel: NavigationViewModel) {
            binding.address = item

            binding.root.setOnClickListener {
                if(viewModel.isStart.value == true) {
                    viewModel.startCoordinate.value = "${item.x},${item.y}"
                    viewModel.startAddressName.value = item.placeName
                } else {
                    viewModel.endCoordinate.value = "${item.x},${item.y}"
                    viewModel.endAddressName.value = item.placeName
                }

                ViewHelper.hideKeyboard(it)
            }
        }
    }
}

private class NavigationAddressDiffCallback : DiffUtil.ItemCallback<AddressItem>() {
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