package com.goldcompany.apps.koreabike.ui.search_address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoAddressItem
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import com.goldcompany.apps.koreabike.databinding.SubSearchAddressItemBinding
import com.goldcompany.apps.koreabike.db.item.UserAddress

class SearchAddressAdapter(private val dataSet: KakaoData,
                           private val viewModel: SearchAddressViewModel
): RecyclerView.Adapter<SearchAddressAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: SubSearchAddressItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val keyword = binding.itemKeyword
        val address = binding.itemAddress

        fun bind(item: KakaoAddressItem) {
            keyword.text = item.placeName
            address.text = item.addressName

            itemView.setOnClickListener {
                val userAddress = UserAddress(
                    latitude = item.y.toDouble(),
                    longitude = item.x.toDouble(),
                    address = item.addressName,
                    keyword = item.placeName,
                    selected = true
                )

                viewModel.setCurrentAddress(userAddress)
                Navigation.findNavController(itemView).popBackStack()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SubSearchAddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet.addressList[position])
    }

    override fun getItemCount() = dataSet.addressList.size
}