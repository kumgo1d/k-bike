package com.goldcompany.apps.koreabike.ui.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
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
import com.goldcompany.apps.koreabike.Constants
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import com.goldcompany.apps.koreabike.util.LoadingStateAdapter
import com.goldcompany.apps.koreabike.util.ViewHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

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
                Constants.RESULT_ERROR -> ViewHelper.errorToast(requireContext())
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
            checkAddressAndNavigateApi()
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
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadState ->
                binding.navigationAddressLoading.isVisible = loadState.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launch {
            viewModel.searchAddress(address)
                .distinctUntilChanged()
                .collect { result ->
                    adapter.submitData(result)
                }
        }
    }

    private fun checkAddressAndNavigateApi() {
        if (!viewModel.isAddressCorrect()) return

        lifecycleScope.launch {
            viewModel.getNavigationPath(
                viewModel.startAddress.value?.coordinate.toString(),
                viewModel.endAddress.value?.coordinate.toString()
            )
                .distinctUntilChanged()
                .collect { result ->
                    if (result.apiNavigationRoute != null) {
                        val bundle = Bundle()
                        val path = result.apiNavigationRoute.comfort[0].path
                        val duration = result.apiNavigationRoute.comfort[0].summary.duration
                        val distance = result.apiNavigationRoute.comfort[0].summary.distance

                        bundle.putInt("duration", duration)
                        bundle.putInt("distance", distance)
                        bundle.putParcelableArrayList("path", path as ArrayList<out Parcelable>)
                        findNavController().navigate(R.id.action_navigationFragment_to_navigationMapFragment, bundle)
                    }
                }
        }
    }

    override fun onStop() {
        super.onStop()
        clearFocus()
    }
}