package com.goldcompany.apps.koreabike.ui.history_place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.databinding.ItemFavoritePlaceListBinding
import com.goldcompany.koreabike.domain.model.Address

class FavoritePlaceAdapter(
    private val setCurrentAddress: (Address) -> Unit,
    private val deleteAddress: (Address) -> Unit
): ListAdapter<Address, FavoritePlaceAdapter.ViewHolder>(HistoryAddressDiffCallback()) {

    inner class ViewHolder(private val binding: ItemFavoritePlaceListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setList(item: Address) {
            binding.address = item

            setDeleteButtonListener(item)
            setItemClickListener(item)
        }

        private fun setDeleteButtonListener(item: Address) {
            binding.placeItemDelete.setOnClickListener {
                deleteAddress(item)
                val list = currentList.toMutableList()
                list.remove(item)
                submitList(list)
            }
        }

        private fun setItemClickListener(item: Address) {
            binding.root.setOnClickListener {
                setCurrentAddress(item)
                Navigation.findNavController(binding.root).navigate(HistoryPlaceFragmentDirections.actionGlobalMapView())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFavoritePlaceListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setList(getItem(position))
    }
}

private class HistoryAddressDiffCallback : DiffUtil.ItemCallback<Address>() {
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