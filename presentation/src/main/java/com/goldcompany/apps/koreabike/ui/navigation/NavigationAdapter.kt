package com.goldcompany.apps.koreabike.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding
import com.goldcompany.apps.koreabike.util.ViewHelper
import com.goldcompany.koreabike.domain.model.Address

class NavigationAdapter(
    private val viewModel: NavigationViewModel
    ): PagingDataAdapter<Address, NavigationAdapter.ViewHolder>(NavigationAddressDiffCallback()) {

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
        fun bind(item: Address, viewModel: NavigationViewModel) {
            binding.address = item

            binding.root.setOnClickListener {
                if (viewModel.isStart.value == true) {
                    val coordinate = "${item.x},${item.y}"
                    viewModel.startAddress.value = NavAddress(item.placeName, coordinate)
                } else {
                    val coordinate = "${item.x},${item.y}"
                    viewModel.endAddress.value = NavAddress(item.placeName, coordinate)
                }
                ViewHelper.hideKeyboard(it)
            }
        }
    }
}

private class NavigationAddressDiffCallback : DiffUtil.ItemCallback<Address>() {
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