package com.goldcompany.apps.koreabike.ui.search_address

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
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
                binding.searchAddressList.adapter = adapter
                binding.searchAddressList.addItemDecoration(AddressAdapterDecoration())
                viewModel.searchAddress(address)
                    .distinctUntilChanged()
                    .collect {
                        adapter.submitList(it.addressList)
                    }
            }
            MainActivity.instance.hideKeyboard(input)
        }
    }

    override fun onStop() {
        super.onStop()
        binding.searchAddressInput.clearFocus()
        MainActivity.instance.hideKeyboard(binding.root)
    }
}