package com.goldcompany.apps.koreabike.ui.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import com.goldcompany.apps.koreabike.util.ListPageTopAppBar
import com.goldcompany.apps.koreabike.util.hideKeyboard
import com.google.android.material.snackbar.Snackbar
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
            .apply {
                navigationAppBar.apply {
                    setViewCompositionStrategy(
                        ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
                    )
                    setContent {
                        MaterialTheme {
                            ListPageTopAppBar(title = R.string.navigation_fragment_title) {
                                findNavController().popBackStack()
                            }
                        }
                    }
                }
            }

        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        adapter = NavigationAdapter(viewModel::setNavAddress)
        binding.addressRecyclerView.addItemDecoration(AddressAdapterDecoration())
        binding.addressRecyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeResultMessage()
        observeSearchAddressList()
        observeNavAddressName()
        observeNavigationPath()

        setTouchListener()
        searchNavAddress()
    }

    private fun observeResultMessage() {
        viewModel.resultMessage.observe(viewLifecycleOwner) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun observeSearchAddressList() {
        viewModel.addressList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    private fun observeNavAddressName() {
        viewModel.startAddress.observe(viewLifecycleOwner) { address ->
            binding.start.setText(address.addressName)
        }

        viewModel.endAddress.observe(viewLifecycleOwner) { address ->
            binding.end.setText(address.addressName)
        }
    }

    private fun observeNavigationPath() {
        viewModel.navigation.observe(viewLifecycleOwner) { result ->
            val bundle = Bundle()
            val path = result.trackList[0].path
            val duration = result.trackList[0].duration
            val distance = result.trackList[0].distance

            bundle.putInt("duration", duration)
            bundle.putInt("distance", distance)
            bundle.putParcelableArrayList("path", path as ArrayList<out Parcelable>)
            findNavController().navigate(R.id.action_navigationFragment_to_navigationMapFragment, bundle)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            clearFocus()
            return@setOnTouchListener true
        }

        binding.navigateButton.setOnClickListener {
            navigateAddress()
        }
    }

    private fun clearFocus() {
        binding.start.clearFocus()
        binding.end.clearFocus()
        hideKeyboard(binding.root)
    }

    private fun searchNavAddress() {
        addressEditTextListener(binding.start)
        addressEditTextListener(binding.end)
    }

    private fun addressEditTextListener(view: EditText) {
        view.setOnKeyListener { v, keyCode, _ ->
            if (v.hasFocus()) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && !view.text.isNullOrEmpty())  {
                    searchAddress(view)
                    clearFocus()
                }
            }
            false
        }
    }

    private fun searchAddress(input: EditText) {
        if (!input.text.isNullOrEmpty()) {
            when (input) {
                binding.start -> {
                    val address = binding.start.text.toString()
                    viewModel.setIsStart(true)
                    viewModel.searchAddress(address, 1)
                }
                binding.end -> {
                    val address = binding.end.text.toString()
                    viewModel.setIsStart(false)
                    viewModel.searchAddress(address, 1)
                }
            }
        }
        input.clearFocus()
        hideKeyboard(input)
    }

    private fun navigateAddress() {
        if (viewModel.isAddressCorrect()) {
            viewModel.getNavigationPath()
        }
    }

    override fun onStop() {
        super.onStop()
        clearFocus()
    }
}