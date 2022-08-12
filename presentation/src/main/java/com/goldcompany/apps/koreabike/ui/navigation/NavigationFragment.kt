package com.goldcompany.apps.koreabike.ui.navigation

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
import com.goldcompany.apps.koreabike.Constants
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import com.goldcompany.apps.koreabike.util.LoadingStateAdapter
import com.goldcompany.apps.koreabike.util.errorToast
import com.goldcompany.apps.koreabike.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationFragment : Fragment() {

    private val viewModel by viewModels<NavigationViewModel>()

    private lateinit var binding: FragmentNavigationBinding
    private lateinit var adapter: NavigationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavigationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeResultMessage()
        setAdapter()
        addressNameObserve()
        setTouchListener()
        searchNavAddress()
    }

    private fun observeResultMessage() {
        viewModel.resultMessage.observe(viewLifecycleOwner) { message ->
            when (message) {
                Constants.RESULT_ERROR -> errorToast(requireContext())
            }
        }
    }

    private fun setAdapter() {
        adapter = NavigationAdapter(viewModel)
        binding.addressRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter(adapter::retry),
            footer = LoadingStateAdapter(adapter::retry)
        )
        binding.addressRecyclerView.addItemDecoration(AddressAdapterDecoration())
    }

    private fun addressNameObserve() {
        viewModel.startAddress.observe(viewLifecycleOwner) { address ->
            binding.start.setText(address.addressName)
        }

        viewModel.endAddress.observe(viewLifecycleOwner) { address ->
            binding.end.setText(address.addressName)
        }
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
//            checkAddressAndNavigateApi()
        }
    }

    private fun clearFocus() {
        binding.start.clearFocus()
        binding.end.clearFocus()
        hideKeyboard(binding.root)
    }

    private fun searchNavAddress() {
        addressEditTextListener(binding.start, true)
        addressEditTextListener(binding.end, false)
    }

    private fun addressEditTextListener(view: EditText, isStart: Boolean) {
        view.setOnKeyListener { v, keyCode, _ ->
            if (v.hasFocus()) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && !view.text.isNullOrEmpty())  {
                    viewModel.isStart.value = isStart
                    searchAddress(view.text.toString())
                    clearFocus()
                }
            }
            false
        }
    }

    private fun searchAddress(address: String) {
//        lifecycleScope.launch {
//            adapter.loadStateFlow.collectLatest { loadState ->
//                binding.navigationAddressLoading.isVisible = loadState.refresh is LoadState.Loading
//            }
//        }
//
//        lifecycleScope.launch {
//            viewModel.searchAddress(address, 0)
//                .distinctUntilChanged()
//                .collect { result ->
//                    adapter.submitData(result)
//                }
//        }
    }

    override fun onStop() {
        super.onStop()
        clearFocus()
    }
}