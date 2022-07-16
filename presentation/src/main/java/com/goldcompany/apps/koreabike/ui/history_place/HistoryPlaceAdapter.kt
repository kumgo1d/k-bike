package com.goldcompany.apps.koreabike.ui.history_place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.databinding.ItemFavoritePlaceListBinding
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress

class FavoritePlaceAdapter(
    private val viewModel: HistoryPlaceViewModel
): ListAdapter<UserHistoryAddress, FavoritePlaceAdapter.ViewHolder>(HistoryAddressDiffCallback()) {

    inner class ViewHolder(private val binding: ItemFavoritePlaceListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setList(item: UserHistoryAddress) {
            binding.address = item

            setDeleteButtonListener(item)
            setItemClickListener(item)
        }

        private fun setDeleteButtonListener(item: UserHistoryAddress) {
            binding.placeItemDelete.setOnClickListener {
                viewModel.deleteAddress(item)
                val list = currentList.toMutableList()
                list.remove(item)
                submitList(list)
            }
        }

        private fun setItemClickListener(item: UserHistoryAddress) {
            binding.root.setOnClickListener {
                viewModel.setCurrentAddress(item)
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

private class HistoryAddressDiffCallback : DiffUtil.ItemCallback<UserHistoryAddress>() {
    override fun areItemsTheSame(
        oldItem: UserHistoryAddress,
        newItem: UserHistoryAddress
    ): Boolean {
        return oldItem.address == newItem.address
    }

    override fun areContentsTheSame(
        oldItem: UserHistoryAddress,
        newItem: UserHistoryAddress
    ): Boolean {
        return oldItem.address == newItem.address
    }
}