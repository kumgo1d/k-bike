package com.goldcompany.apps.koreabike.ui.search_address

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchAddressFragment : Fragment() {

    private val viewModel by viewModels<SearchAddressViewModel>()

    private lateinit var binding: FragmentSearchAddressBinding
    private lateinit var adapter: SearchAddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchAddressBinding.inflate(inflater, container, false)
        adapter = SearchAddressAdapter(viewModel)

        MainActivity.instance.hideBottom()
        setButtonListener()

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setButtonListener() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            binding.searchAddressInput.clearFocus()
            MainActivity.instance.hideKeyboard(binding.root)
            return@setOnTouchListener true
        }

        binding.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.searchAddressButton.setOnClickListener {
            binding.searchAddressInput.clearFocus()
            MainActivity.instance.hideKeyboard(binding.searchAddressInput)
            searchAddress()
        }

        binding.favoriteAddressButton.setOnClickListener {
            val direction = SearchAddressFragmentDirections.actionSearchAddressFragmentToFavoritePlaceFragment()
            findNavController().navigate(direction)
        }
    }

    private fun searchAddress() {
        val address = binding.searchAddressInput.text.toString()
        lifecycleScope.launch {
            binding.searchAddressList.adapter = adapter
            viewModel.searchAddress(address)
                .distinctUntilChanged()
                .collect {
                    adapter.submitList(it.addressList)
                }
        }
    }

    override fun onStop() {
        super.onStop()
        binding.searchAddressInput.clearFocus()
        MainActivity.instance.hideKeyboard(binding.root)
    }
}