package com.goldcompany.apps.koreabike.ui.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.data.Result
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import com.goldcompany.apps.koreabike.util.ViewHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.ArrayList

@AndroidEntryPoint
class NavigationFragment : Fragment() {

    private lateinit var binding: FragmentNavigationBinding
    private lateinit var adapter: NavigationAdapter

    private val viewModel by viewModels<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavigationBinding.inflate(layoutInflater, container, false)

        MainActivity.instance.hideBottom()
        setAdapter()
        addressNameObserve()
        setTouchListener()
        searchNavAddress()

        return binding.root
    }

    private fun setAdapter() {
        adapter = NavigationAdapter(viewModel)
        binding.addressRecyclerView.adapter = adapter
        binding.addressRecyclerView.addItemDecoration(AddressAdapterDecoration())
    }

    private fun addressNameObserve() {
        val startAddressObserver = Observer<String> { address ->
            binding.start.setText(address)
        }

        viewModel.startAddressName.observe(viewLifecycleOwner, startAddressObserver)

        val endAddressObserver = Observer<String> { address ->
            binding.end.setText(address)
        }

        viewModel.endAddressName.observe(viewLifecycleOwner, endAddressObserver)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            clearFocus()
            return@setOnTouchListener true
        }

        binding.navigationAppBar.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.navigateButton.setOnClickListener {
            lifecycleScope.launch {
                checkAddressAndNavigateApi()
            }
        }
    }

    private fun clearFocus() {
        binding.start.clearFocus()
        binding.end.clearFocus()
        ViewHelper.hideKeyboard(binding.root)
    }

    private fun searchNavAddress() {
        addressEditTextListener(binding.start, true)
        addressEditTextListener(binding.end, false)
    }

    private fun addressEditTextListener(view: EditText, isStart: Boolean) {
        view.setOnClickListener {
            binding.addressRecyclerView.adapter = null
        }

        view.setOnKeyListener { _, keyCode, _ ->
            if(keyCode == KeyEvent.KEYCODE_ENTER && !view.text.isNullOrEmpty())  {
                viewModel.isStart.value = isStart
                searchAddress(view.text.toString())
                clearFocus()
            }
            false
        }
    }

    private fun searchAddress(address: String) {
        lifecycleScope.launch {
            viewModel.searchAddress(address)
                .distinctUntilChanged()
                .collect {
                    if(it is Result.Success) {
                        adapter.submitList(it.data.addressList)
                    } else {
                        ViewHelper.errorToast(requireContext())
                    }
                }
        }
    }

    private fun checkAddressAndNavigateApi() {
        if(!viewModel.isAddressNullOrSame()) {
            Toast.makeText(requireContext(), R.string.wrong_address_input, Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            viewModel.getNavigationPath()
                .distinctUntilChanged()
                .collect {
                    if(it is Result.Success) {
                        val bundle = Bundle()
                        val path = it.data.route.track[0].path
                        val duration = it.data.route.track[0].summary.duration
                        val distance = it.data.route.track[0].summary.distance

                        bundle.putInt("duration", duration)
                        bundle.putInt("distance", distance)
                        bundle.putParcelableArrayList("path", path as ArrayList<out Parcelable>)
                        findNavController().navigate(R.id.action_navigationFragment_to_navigationMapFragment, bundle)
                    } else {
                        ViewHelper.errorToast(requireContext())
                    }
                }
        }
    }

    override fun onStop() {
        super.onStop()
        clearFocus()
    }
}