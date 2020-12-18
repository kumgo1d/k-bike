package com.android.apps.seoulpublicbike

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_favorite_list.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.Holder>() {
    var helper: FavoriteListItemHelper? = null
    var listItem = mutableListOf<FavoriteListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = listItem.get(position)
        holder.setList(data)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mListItem: FavoriteListItem? = null

        init {
            helper?.FavoriteListItemDAO()?.delete(mListItem!!)
            listItem.remove(mListItem)
            notifyDataSetChanged()
        }

        fun setList(item: FavoriteListItem) {
            itemView.item_station.text = item.station
            itemView.item_parking.text = item.parkingBike
            itemView.item_rack.text = item.rackBike
        }
    }
}