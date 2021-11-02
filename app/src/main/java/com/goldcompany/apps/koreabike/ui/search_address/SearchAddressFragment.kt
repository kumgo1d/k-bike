package com.goldcompany.apps.koreabike.ui.search_address

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.adapter.SearchAddressAdapter
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBinding
import com.goldcompany.apps.koreabike.api.FindPlaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.sub_search_address_item.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchAddressFragment : Fragment() {
    private lateinit var binding: FragmentSearchAddressBinding
    private lateinit var viewModel: SearchAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_address, container, false)

        viewModel = ViewModelProvider(this).get(SearchAddressViewModel::class.java)

        MainActivity.hideBottom()
        setButtonListener()

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        binding.searchAddressInput.clearFocus()
        MainActivity.hideKeyboard(binding.root)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setButtonListener() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            binding.searchAddressInput.clearFocus()
            MainActivity.hideKeyboard(binding.root)
            return@setOnTouchListener true
        }

        binding.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.searchAddressButton.setOnClickListener {
            binding.searchAddressInput.clearFocus()
            MainActivity.hideKeyboard(binding.searchAddressInput)
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
        FindPlaces().callKakaoKeyword(address = address) { data, _ ->
            if(data == null) {
                Toast.makeText(requireContext(), "해당 주소를 찾지 못하였습니다.", Toast.LENGTH_SHORT).show()
                return@callKakaoKeyword
            }

            binding.searchAddressList.adapter = SearchAddressAdapter(data, viewModel)
        }
    }
}