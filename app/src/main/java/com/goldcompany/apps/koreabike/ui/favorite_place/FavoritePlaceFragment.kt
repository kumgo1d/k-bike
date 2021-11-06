package com.goldcompany.apps.koreabike.ui.favorite_place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentFavoritePlaceBinding
import com.goldcompany.apps.koreabike.db.item.UserAddress
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

        viewModel.getAddress().observe(viewLifecycleOwner) {
            binding.favoriteAddressList.adapter =
                FavoritePlaceAdapter(it, ::deleteItem, ::updateItem)
        }

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
            date = System.currentTimeMillis(),
            longitude = address.longitude,
            latitude = address.latitude,
            address = address.address,
            keyword = address.keyword,
            selected = true
        )

        viewModel.setCurrentAddress(selected)

        findNavController().navigate(FavoritePlaceFragmentDirections.actionGlobalMapView())
    }

    private fun addListener() {
        binding.appBar.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}