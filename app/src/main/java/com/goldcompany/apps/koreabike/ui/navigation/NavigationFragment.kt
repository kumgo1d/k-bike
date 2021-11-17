package com.goldcompany.apps.koreabike.ui.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
        setTouchListener()
        searchNavAddress()

        return binding.root
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
        MainActivity.instance.hideKeyboard(binding.root)
    }

    private fun searchNavAddress() {
        startAddressListener()
        endAddressListener()
    }

    private fun startAddressListener() {
        val start = binding.start
        start.setOnClickListener {
            binding.addressRecyclerView.adapter = null
        }
        start.setOnKeyListener(enterKeyListener(start.text.toString(), true))
    }

    private fun endAddressListener() {
        val end = binding.end
        end.setOnClickListener {
            binding.addressRecyclerView.adapter = null
        }
        end.setOnKeyListener(enterKeyListener(end.text.toString(), false))
    }

    private fun enterKeyListener(str: String, isStart: Boolean) = View.OnKeyListener { v, keyCode, event ->
        if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            if(str.isNotEmpty()) {
                searchAddress(str, isStart)
            }
        }
        false
    }

    private fun searchAddress(address: String, isStart: Boolean) {
        adapter = NavigationAdapter(viewModel, isStart)
        binding.addressRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.searchAddress(address)
                .distinctUntilChanged()
                .collect {
                    adapter.submitList(it.addressList)
                }
        }
    }

    private fun checkAddressAndNavigateApi() {
        if(!viewModel.isAddressNullOrSame()) {
            Toast.makeText(requireContext(), "주소를 잘못 입력하였습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            viewModel.getNavigationPath()
                .distinctUntilChanged()
                .collect {
                    val bundle = Bundle()
                    val path = it.route.track[0].path
                    val duration = it.route.track[0].summary.duration
                    val distance = it.route.track[0].summary.distance

                    bundle.putInt("duration", duration)
                    bundle.putInt("distance", distance)
                    bundle.putParcelableArrayList("path", path as ArrayList<out Parcelable>)
                    findNavController().navigate(R.id.action_navigationFragment_to_navigationMapFragment, bundle)
                }
        }
    }

    override fun onStop() {
        super.onStop()
        clearFocus()
    }
}