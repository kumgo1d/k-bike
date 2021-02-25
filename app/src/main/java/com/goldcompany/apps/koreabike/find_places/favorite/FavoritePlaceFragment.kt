package com.goldcompany.apps.koreabike.find_places.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentFavoritePlaceBinding
import com.goldcompany.apps.koreabike.find_places.kakaodata.KakaoData

class FavoritePlaceFragment : Fragment() {
    private lateinit var binding: FragmentFavoritePlaceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_favorite_place, container, false)

        addListener()

        return binding.root
    }

    private fun addListener() {
        binding.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}

class FavoritePlaceAdapter(private val dataSet: KakaoData): RecyclerView.Adapter<FavoritePlaceAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val keyword: TextView = view.findViewById(R.id.item_keyword)
        val address: TextView = view.findViewById(R.id.item_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_search_address_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.keyword.text = dataSet.documents[position].place_name
        holder.address.text = dataSet.documents[position].address_name

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount() = dataSet.documents.size
}