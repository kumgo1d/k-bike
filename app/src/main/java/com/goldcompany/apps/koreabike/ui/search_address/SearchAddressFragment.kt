package com.goldcompany.apps.koreabike.ui.search_address

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchAddressFragment : Fragment() {

    private val viewModel by viewModels<SearchAddressViewModel>()

    private lateinit var binding: FragmentSearchAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchAddressBinding.inflate(inflater, container, false)

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
            lifecycleScope.launch {
                searchAddress()
            }
        }

        binding.favoriteAddressButton.setOnClickListener {
            findNavController().navigate(SearchAddressFragmentDirections.actionSearchAddressFragmentToFavoritePlaceFragment())
        }
    }

    private fun searchAddress() {
        val address = binding.searchAddressInput.text.toString()
        lifecycleScope.launch {
            val resultAddress = viewModel.getAddress(address)
            binding.searchAddressList.adapter = SearchAddressAdapter(resultAddress, viewModel)
        }
    }

    override fun onStop() {
        super.onStop()
        binding.searchAddressInput.clearFocus()
        MainActivity.instance.hideKeyboard(binding.root)
    }
}