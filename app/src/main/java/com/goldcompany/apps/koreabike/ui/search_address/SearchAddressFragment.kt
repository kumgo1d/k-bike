package com.goldcompany.apps.koreabike.ui.search_address

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.isVisible
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
        adapter = SearchAddressAdapter(viewModel)

        MainActivity.instance.hideBottom()
        setAdapter()
        setButtonListener()

        return binding.root
    }

    private fun setAdapter() {
        binding.searchAddressList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter(adapter::retry),
            footer = LoadingStateAdapter(adapter::retry)
        )
        binding.searchAddressList.addItemDecoration(AddressAdapterDecoration())
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setButtonListener() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            binding.searchAddressInput.clearFocus()
            ViewHelper.hideKeyboard(binding.root)
            return@setOnTouchListener true
        }

        binding.favoriteAddressButton.setOnClickListener {
            val direction = SearchAddressFragmentDirections.actionSearchAddressFragmentToFavoritePlaceFragment()
            findNavController().navigate(direction)
        }

        binding.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.searchAddressInput.setOnKeyListener { _, keyCode, _ ->
            when(keyCode) {
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

    private fun searchAddress(input: AppCompatEditText) {
        input.clearFocus()

        if(!input.text.isNullOrEmpty()) {
            val address = binding.searchAddressInput.text.toString()

            lifecycleScope.launch {
                viewModel.searchAddress(address).collectLatest { result ->
                    adapter.submitData(result)
                }
            }

            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest { loadState ->
                    binding.searchAddressLoading.isVisible = loadState.refresh is LoadState.Loading
                }
            }
            ViewHelper.hideKeyboard(input)
        }
    }

    override fun onStop() {
        super.onStop()
        binding.searchAddressInput.clearFocus()
        ViewHelper.hideKeyboard(binding.root)
    }
}