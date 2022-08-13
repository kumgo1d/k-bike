package com.goldcompany.apps.koreabike.ui.search_address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding
import com.goldcompany.apps.koreabike.util.AddressDiffUtil
import com.goldcompany.koreabike.domain.model.Address

class SearchAddressAdapter(
    private val setCurrentAddress: (Address) -> Unit
): ListAdapter<Address, SearchAddressAdapter.ViewHolder>(AddressDiffUtil) {

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
                setCurrentAddress(item)
                Navigation.findNavController(itemView).popBackStack()
            }
        }
    }
}