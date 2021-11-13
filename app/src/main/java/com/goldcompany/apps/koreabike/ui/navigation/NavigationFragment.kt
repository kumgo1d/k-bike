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
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.api.NaverApiRetrofitClient
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.ArrayList

@AndroidEntryPoint
class NavigationFragment : Fragment() {

    private val viewModel by viewModels<NavigationViewModel>()
    private val NAVER_API_KEY_ID = "fe7iwsbkl5"
    private val NAVER_API_KEY = "1KYsy93nxRaNmfxdHExFfyAIX89B8sfwePQw7bNP"

    private lateinit var binding: FragmentNavigationBinding
    private lateinit var adapter: NavigationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavigationBinding.inflate(layoutInflater, container, false)

        MainActivity.instance.hideBottom()
        setTouchListener()
        bindNavAddress()
        searchNavAddress()

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            binding.start.clearFocus()
            binding.end.clearFocus()
            MainActivity.instance.hideKeyboard(binding.root)
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

    private fun bindNavAddress() {
        val startPlace = Observer<String> { start ->
            binding.start.setText(start)
        }

        val endPlace = Observer<String> { end ->
            binding.end.setText(end)
        }

        viewModel.startAddress.observe(requireActivity(), startPlace)
        viewModel.endAddress.observe(requireActivity(), endPlace)
    }

    private fun searchNavAddress() {
        binding.start.setOnClickListener {
            binding.addressRecyclerView.adapter = null
        }
        binding.start.addTextChangedListener(textChangeListener(true))
        binding.start.setOnKeyListener(enterKeyListener())

        binding.end.setOnClickListener {
            binding.addressRecyclerView.adapter = null
        }
        binding.end.addTextChangedListener(textChangeListener(false))
        binding.end.setOnKeyListener(enterKeyListener())
    }

    private fun textChangeListener(isStart: Boolean) = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            lifecycleScope.launch {
                delay(1000)
                if(s != null && s.isNotEmpty()) {
                    searchAddress(s.toString(), isStart)
                }
            }
        }
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

    private fun enterKeyListener() = View.OnKeyListener { _, keyCode, event ->
        if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            checkAddressAndNavigateApi()
        }
        false
    }

    private fun checkAddressAndNavigateApi() {
        if(!checkRequiredAllAddress()) return

        lifecycleScope.launch {
            viewModel.getNavigationPath()
                .distinctUntilChanged()
                .collect {
                    val path = it.route.traComfort[0].path
                    val duration = it.route.traComfort[0].summary.duration
                    val distance = it.route.traComfort[0].summary.distance
                    val bundle = Bundle()

                    bundle.putInt("duration", duration)
                    bundle.putInt("distance", distance)
                    bundle.putParcelableArrayList("path", path as ArrayList<out Parcelable>)
                    findNavController().navigate(R.id.action_navigationFragment_to_navigationMapFragment, bundle)
                }
        }
    }

    private fun checkRequiredAllAddress(): Boolean {
        if(viewModel.startX.isEmpty() || viewModel.endX.isEmpty()) {
            Toast.makeText(requireContext(), "주소를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return false
        } else if(viewModel.startAddress.value == viewModel.endAddress.value) {
            Toast.makeText(requireContext(), "다른 주소를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onStop() {
        super.onStop()
        binding.start.clearFocus()
        binding.end.clearFocus()
        MainActivity.instance.hideKeyboard(binding.root)
    }
}