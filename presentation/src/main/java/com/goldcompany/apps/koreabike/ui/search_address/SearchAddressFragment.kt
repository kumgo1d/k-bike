package com.goldcompany.apps.koreabike.ui.search_address

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import com.goldcompany.apps.koreabike.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

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
        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        adapter = SearchAddressAdapter(viewModel::setCurrentAddress)
        binding.searchAddressList.adapter = adapter
        binding.searchAddressList.addItemDecoration(AddressAdapterDecoration())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSearchAddressList()
        setButtonListener()
    }

    private fun observeSearchAddressList() {
        viewModel.addressList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setButtonListener() {
        touchScreenListenerForHideKeyboard()
        goToFavoriteFragmentButtonListener()
        backButtonListener()
        okButtonListenerForSearchAddress()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun touchScreenListenerForHideKeyboard() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            binding.searchAddressInput.clearFocus()
            hideKeyboard(binding.root)
            return@setOnTouchListener true
        }
    }

    private fun goToFavoriteFragmentButtonListener() {
        binding.favoriteAddressButton.setOnClickListener {
            val direction = SearchAddressFragmentDirections.actionSearchAddressFragmentToFavoritePlaceFragment()
            findNavController().navigate(direction)
        }
    }

    private fun backButtonListener() {
        binding.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun okButtonListenerForSearchAddress() {
        binding.searchAddressInput.setOnKeyListener { _, keyCode, _ ->
            when (keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
                    searchAddress(binding.searchAddressInput)
                    true
                }
                else -> { false }
            }
        }

        binding.searchAddressButton.setOnClickListener {
            searchAddress(binding.searchAddressInput)
        }
    }

    private fun searchAddress(input: EditText) {
        if (!input.text.isNullOrEmpty()) {
            val address = binding.searchAddressInput.text.toString()
            viewModel.searchAddress(address, 1)
        }
        input.clearFocus()
        hideKeyboard(input)
    }

    override fun onStop() {
        super.onStop()
        binding.searchAddressInput.clearFocus()
        hideKeyboard(binding.root)
    }
}