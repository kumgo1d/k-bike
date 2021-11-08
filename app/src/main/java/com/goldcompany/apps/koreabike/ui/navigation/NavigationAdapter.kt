package com.goldcompany.apps.koreabike.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoAddressItem
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import com.goldcompany.apps.koreabike.databinding.SubSearchAddressItemBinding

class NavigationAdapter(
    private val viewModel: NavigationViewModel,
    private val isStart: Boolean
    ): ListAdapter<KakaoAddressItem, NavigationAdapter.ViewHolder>(NavigationAddressDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SubSearchAddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel, isStart)
    }

    class ViewHolder(private val binding: SubSearchAddressItemBinding): RecyclerView.ViewHolder(binding.root) {
        val keyword = binding.itemKeyword
        val address = binding.itemAddress

        fun bind(item: KakaoAddressItem, viewModel: NavigationViewModel, isStart: Boolean) {
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

private class NavigationAddressDiffCallback : DiffUtil.ItemCallback<KakaoAddressItem>() {
    override fun areItemsTheSame(
        oldItem: KakaoAddressItem,
        newItem: KakaoAddressItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: KakaoAddressItem,
        newItem: KakaoAddressItem
    ): Boolean {
        return oldItem == newItem
    }
}