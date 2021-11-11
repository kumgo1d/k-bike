package com.goldcompany.apps.koreabike.ui.history_place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentFavoritePlaceBinding


class HistoryPlaceFragment : Fragment() {

    private val viewModel by viewModels<HistoryPlaceViewModel>()

    private lateinit var binding: FragmentFavoritePlaceBinding
    private lateinit var adapter: FavoritePlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_favorite_place, container, false)

        setAdapter()
        addListener()

        return binding.root
    }

    private fun setAdapter() {
        adapter = FavoritePlaceAdapter(viewModel)
        binding.favoriteAddressList.adapter = adapter

        viewModel.getAddress().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun addListener() {
        binding.appBar.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}