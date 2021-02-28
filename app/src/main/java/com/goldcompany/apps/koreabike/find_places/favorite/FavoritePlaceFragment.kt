package com.goldcompany.apps.koreabike.find_places.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentFavoritePlaceBinding
import com.goldcompany.apps.koreabike.db.item.UserAddress


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
            binding.favoriteAddressList.adapter = FavoritePlaceAdapter(it)
        })

        addListener()

        return binding.root
    }

    private fun addListener() {
        binding.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}

class FavoritePlaceAdapter(private val dataSet: List<UserAddress>?): RecyclerView.Adapter<FavoritePlaceAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val keyword: TextView = view.findViewById(R.id.place_item_keyword)
        val address: TextView = view.findViewById(R.id.place_item_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_favorite_place_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.keyword.text = dataSet?.get(position)?.keyword
        holder.address.text = dataSet?.get(position)?.address

        holder.itemView.setOnClickListener {
            //selected가 바뀌어야 하고
            //좌표를 보내줘야 하고
            //map fragment로 이동해야 함
            
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0
}