package com.goldcompany.apps.koreabike.ui.search_address

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import com.goldcompany.apps.koreabike.util.LoadingStateAdapter
import com.goldcompany.apps.koreabike.util.ViewHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

        setAdapter()
        setButtonListener()

        return binding.root
    }

    private fun setAdapter() {
        adapter = SearchAddressAdapter(viewModel)
        binding.searchAddressList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter(adapter::retry),
            footer = LoadingStateAdapter(adapter::retry)
        )
        binding.searchAddressList.addItemDecoration(AddressAdapterDecoration())
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
            ViewHelper.hideKeyboard(binding.root)
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
        input.clearFocus()

        if (!input.text.isNullOrEmpty()) {
            val address = binding.searchAddressInput.text.toString()

            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest { loadState ->
                    binding.searchAddressLoading.isVisible = loadState.refresh is LoadState.Loading
                }
            }

            lifecycleScope.launch {
                viewModel.searchAddress(address).collectLatest { result ->
                    adapter.submitData(result)
                }
            }
        }

        ViewHelper.hideKeyboard(input)
    }

    override fun onStop() {
        super.onStop()
        binding.searchAddressInput.clearFocus()
        ViewHelper.hideKeyboard(binding.root)
    }
}