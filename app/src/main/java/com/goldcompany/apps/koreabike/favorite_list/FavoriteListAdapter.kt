package com.goldcompany.apps.koreabike.favorite_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import kotlinx.android.synthetic.main.sub_favorite_list_item.view.*

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.Holder>() {
    var helper: KBikeDatabase? = null
    var listItem = mutableListOf<FavoriteListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_favorite_list_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = listItem[position]
        holder.setList(data)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mItem: FavoriteListItem? = null

        init {

            itemView.delete_button.setOnClickListener {
                helper?.FavoriteListItemDAO()?.delete(mItem!!)
                listItem.remove(mItem)
                notifyDataSetChanged()
            }
        }

        @SuppressLint("SetTextI18n")
        fun setList(item: FavoriteListItem) {
            itemView.item_station.text = item.station
            itemView.item_parking.text = "자전거 : " + item.parkingBike
            itemView.item_rack.text = "주차가능 : " + item.rackBike

            this.mItem = item
        }
    }
}