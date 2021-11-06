package com.goldcompany.apps.koreabike.ui.favorite_place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.db.item.UserAddress
import kotlinx.android.synthetic.main.sub_favorite_place_list_item.view.*

class FavoritePlaceAdapter(private val list: MutableList<UserAddress>?,
                           private val deleteItem: (UserAddress) -> Unit,
                           private val updateItem: (UserAddress) -> Unit): RecyclerView.Adapter<FavoritePlaceAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var item: UserAddress? = null

        init {
            itemView.place_item_delete.setOnClickListener {
                deleteItem(item!!)
                list?.remove(item!!)
                notifyDataSetChanged()
            }

            itemView.setOnClickListener {
                updateItem(item!!)
            }
        }

        fun setList(item: UserAddress) {
            itemView.place_item_keyword.text = item.keyword
            itemView.place_item_address.text = item.address

            this.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_favorite_place_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setList(list?.get(position)!!)
    }

    override fun getItemCount() = list?.size ?: 0
}