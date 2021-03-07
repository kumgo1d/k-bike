package com.goldcompany.apps.koreabike.find_places.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentFavoritePlaceBinding
import com.goldcompany.apps.koreabike.db.item.UserAddress
import kotlinx.android.synthetic.main.sub_favorite_place_list_item.view.*
import kotlinx.coroutines.launch


class FavoritePlaceFragment : Fragment() {
    private lateinit var binding: FragmentFavoritePlaceBinding
    private lateinit var viewModel: FavoritePlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_favorite_place, container, false)

        viewModel = ViewModelProvider(this).get(FavoritePlaceViewModel::class.java)

        viewModel.getAddress().observe(viewLifecycleOwner, {
            binding.favoriteAddressList.adapter = FavoritePlaceAdapter(it, ::deleteItem, ::updateItem)
        })

        addListener()

        return binding.root
    }

    private fun deleteItem(address: UserAddress) {
        lifecycleScope.launch {
            KBikeApplication.instance.database.UserAddressDAO().delete(address)
        }
    }

    private fun updateItem(address: UserAddress) {
        val selected = UserAddress(
            date = address.date,
            longitude = address.longitude,
            latitude = address.latitude,
            address = address.address,
            keyword = address.keyword,
            selected = true
        )

        viewModel.setCurrentAddress(selected)

        findNavController().navigate(FavoritePlaceFragmentDirections.actionFavoritePlaceFragmentToHomeFragment())
    }

    private fun addListener() {
        binding.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}

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