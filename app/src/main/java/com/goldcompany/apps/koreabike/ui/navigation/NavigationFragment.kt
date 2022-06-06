package com.goldcompany.apps.koreabike.ui.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import com.goldcompany.apps.koreabike.util.LoadingStateAdapter
import com.goldcompany.apps.koreabike.util.Result
import com.goldcompany.apps.koreabike.util.ViewHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

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

        return binding.root
    }

    private fun setAdapter() {
        adapter = NavigationAdapter(viewModel)
        binding.addressRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter(adapter::retry),
            footer = LoadingStateAdapter(adapter::retry)
        )
        binding.addressRecyclerView.addItemDecoration(AddressAdapterDecoration())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddressName()
        setTouchListener()
        searchNavAddress()
    }

    private fun observeAddressName() {
        viewModel.startAddressName.observe(viewLifecycleOwner) { address ->
            binding.start.setText(address)
        }

        viewModel.endAddressName.observe(viewLifecycleOwner) { address ->
            binding.end.setText(address)
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
        view.setOnClickListener {
            binding.addressRecyclerView.adapter = null
        }

        view.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && !view.text.isNullOrEmpty())  {
                viewModel.isStart.value = isStart
                searchAddress(view.text.toString())
                clearFocus()
            }
            false
        }
    }

    private fun searchAddress(address: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                adapter.loadStateFlow.collectLatest { loadState ->
                    binding.navigationAddressLoading.isVisible = loadState.refresh is LoadState.Loading
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.searchAddress(address)
                    .distinctUntilChanged()
                    .collect { result ->
                        adapter.submitData(result)
                    }
            }
        }
    }

    private fun checkAddressAndNavigateApi() {
        if (!viewModel.isAddressNullOrSame()) {
            Toast.makeText(requireContext(), R.string.wrong_address_input, Toast.LENGTH_SHORT).show()
            return
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getNavigationPath()
                    .distinctUntilChanged()
                    .collect {
                        if (it is Result.Success) {
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
    }

    override fun onStop() {
        super.onStop()
        clearFocus()
    }
}