package com.goldcompany.apps.koreabike.ui.history_place

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.databinding.SubFavoritePlaceListItemBinding
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import kotlinx.android.synthetic.main.sub_favorite_place_list_item.view.*

class FavoritePlaceAdapter(
    private val viewModel: HistoryPlaceViewModel
): ListAdapter<UserHistoryAddress, FavoritePlaceAdapter.ViewHolder>(HistoryAddressDiffCallback()) {

    inner class ViewHolder(private val binding: SubFavoritePlaceListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var item: UserHistoryAddress? = null

        init {
            binding.placeItemDelete.setOnClickListener {
                viewModel.deleteAddress(item!!)
                val list = currentList.toMutableList()
                list.remove(item!!)
                submitList(list)
            }

            binding.root.setOnClickListener {
                viewModel.setCurrentAddress(item!!)
                Navigation.findNavController(binding.root).navigate(HistoryPlaceFragmentDirections.actionGlobalMapView())
            }
        }

        fun setList(item: UserHistoryAddress) {
            binding.address = item
            this.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SubFavoritePlaceListItemBinding.inflate(
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