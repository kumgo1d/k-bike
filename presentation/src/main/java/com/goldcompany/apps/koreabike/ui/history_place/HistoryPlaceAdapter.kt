package com.goldcompany.apps.koreabike.ui.history_place

import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.compose.HistoryPlaceItemView
import com.goldcompany.apps.koreabike.util.AddressDiffUtil
import com.goldcompany.koreabike.domain.model.address.Address
import kotlinx.coroutines.*

class FavoritePlaceAdapter(
    private val setCurrentAddress: (Address) -> Unit,
    private val deleteAddress: (Address) -> Unit
): ListAdapter<Address, FavoritePlaceAdapter.ViewHolder>(AddressDiffUtil) {

    inner class ViewHolder(composeView: ComposeView) : RecyclerView.ViewHolder(composeView) {
        fun bind(address: Address) {
            (itemView as ComposeView).setContent {
                MaterialTheme {
                    HistoryPlaceItemView(
                        address = address,
                        deleteAddress = {
                            deleteAddress(address)
                        },
                        onClick = {
                            CoroutineScope(Dispatchers.Main).launch {
                                val setAddress = CoroutineScope(Dispatchers.IO).async {
                                    setCurrentAddress(address)
                                }
                                setAddress.await()
                                Navigation.findNavController(itemView).navigate(HistoryPlaceFragmentDirections.actionGlobalMapView())
                            }
                        }
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ComposeView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}