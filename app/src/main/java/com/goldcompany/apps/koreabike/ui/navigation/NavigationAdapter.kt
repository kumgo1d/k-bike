package com.goldcompany.apps.koreabike.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding

class NavigationAdapter(
    private val viewModel: NavigationViewModel,
    private val isStart: Boolean
    ): ListAdapter<AddressItem, NavigationAdapter.ViewHolder>(NavigationAddressDiffCallback()) {

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
        holder.bind(getItem(position), viewModel, isStart)
    }

    class ViewHolder(private val binding: ItemSearchAddressBinding): RecyclerView.ViewHolder(binding.root) {
        val keyword = binding.itemKeyword
        val address = binding.itemAddress

        fun bind(item: AddressItem, viewModel: NavigationViewModel, isStart: Boolean) {
            keyword.text = item.placeName
            address.text = item.addressName

            binding.root.setOnClickListener {
                if(isStart) {
                    viewModel.startAddress.value = item.placeName
                    viewModel.startX = item.x
                    viewModel.startY = item.y
                } else {
                    viewModel.endAddress.value = item.placeName
                    viewModel.endX = item.x
                    viewModel.endY = item.y
                }

                MainActivity.instance.hideKeyboard(it)
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