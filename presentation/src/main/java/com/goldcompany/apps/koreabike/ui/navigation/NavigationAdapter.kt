package com.goldcompany.apps.koreabike.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding
import com.goldcompany.apps.koreabike.util.hideKeyboard
import com.goldcompany.koreabike.domain.model.address.Address

class NavigationAdapter(
    private val setNavAddress: (NavAddress) -> Unit
): ListAdapter<Address, NavigationAdapter.ViewHolder>(NavigationAddressDiffCallback()) {

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

    inner class ViewHolder(private val binding: ItemSearchAddressBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Address) {
            binding.address = item
            itemView.setOnClickListener {
                val coordinate = "${item.x},${item.y}"
                setNavAddress(NavAddress(item.placeName, coordinate))
                hideKeyboard(it)
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