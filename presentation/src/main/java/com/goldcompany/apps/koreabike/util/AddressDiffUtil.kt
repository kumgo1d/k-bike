package com.goldcompany.apps.koreabike.util

import androidx.recyclerview.widget.DiffUtil
import com.goldcompany.koreabike.domain.model.address.Address

object AddressDiffUtil : DiffUtil.ItemCallback<Address>() {
    override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem == newItem
    }
}