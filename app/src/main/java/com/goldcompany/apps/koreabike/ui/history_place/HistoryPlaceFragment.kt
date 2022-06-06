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
import com.goldcompany.apps.koreabike.databinding.FragmentHistoryPlaceBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryPlaceFragment : Fragment() {

    private val viewModel by viewModels<HistoryPlaceViewModel>()

    private lateinit var binding: FragmentHistoryPlaceBinding
    private lateinit var adapter: FavoritePlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_history_place, container, false)

        setAdapter()
        addListener()

        return binding.root
    }

    private fun setAdapter() {
        adapter = FavoritePlaceAdapter(viewModel)
        binding.favoriteAddressList.adapter = adapter
        binding.favoriteAddressList.addItemDecoration(AddressAdapterDecoration())
    }

    private fun addListener() {
        binding.appBar.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddress()
    }

    private fun observeAddress() {
        viewModel.addressList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}