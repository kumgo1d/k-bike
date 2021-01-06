package com.android.apps.seoulpublicbike.favoritelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.apps.seoulpublicbike.R
import kotlinx.android.synthetic.main.item_favorite_list.view.*

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.Holder>() {
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
        var mItem: FavoriteListItem? = null
        init {
            //즐겨찾기 삭제 버튼 - ROOM 데이터베이스에서 삭제
            itemView.delete_button.setOnClickListener {
                helper?.FavoriteListItemDAO()?.delete(mItem!!)
                listItem.remove(mItem)
                notifyDataSetChanged()
            }
        }
        fun setList(item: FavoriteListItem) {
            itemView.item_station.text = item.station
            itemView.item_parking.text = "자전거 : " + item.parkingBike
            itemView.item_rack.text = "주차가능 : " + item.rackBike

            this.mItem = item
        }
    }
}