package com.goldcompany.apps.koreabike.favorite_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import kotlinx.android.synthetic.main.sub_favorite_list_item.view.*

class FavoriteListAdapter(private val list: MutableList<FavoriteListItem>,
                          private val deleteItem: (FavoriteListItem) -> Unit) : RecyclerView.Adapter<FavoriteListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_favorite_list_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.setList(data)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentItem: FavoriteListItem? = null

        init {
            itemView.delete_button.setOnClickListener {
                deleteItem(currentItem!!)
                list.remove(currentItem)
                notifyDataSetChanged()
            }
        }

        @SuppressLint("SetTextI18n")
        fun setList(item: FavoriteListItem) {
            itemView.item_station.text = item.station
            itemView.item_parking.text = item.parkingBike
            itemView.item_rack.text = item.rackBike

            this.currentItem = item
        }
    }
}