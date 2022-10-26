package com.goldcompany.apps.koreabike.ui.history_place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentHistoryPlaceBinding
import com.goldcompany.apps.koreabike.util.AddressAdapterDecoration
import com.goldcompany.apps.koreabike.util.ListPageTopAppBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryPlaceFragment : Fragment() {

    private val viewModel by viewModels<HistoryPlaceViewModel>()

    private lateinit var binding: FragmentHistoryPlaceBinding
    private lateinit var adapter: FavoritePlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentHistoryPlaceBinding?>(layoutInflater, R.layout.fragment_history_place, container, false)
            .apply {
                appBar.apply {
                    setViewCompositionStrategy(
                        ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
                    )

                    setContent {
                        MaterialTheme {
                            ListPageTopAppBar(title = R.string.search_list) {
                                findNavController().popBackStack()
                            }
                        }
                    }
                }
            }

        initPlacesAdapter()
        getHistoryPlaces()

        return binding.root
    }

    private fun initPlacesAdapter() {
        adapter = FavoritePlaceAdapter(
            setCurrentAddress = viewModel::setCurrentAddress,
            deleteAddress = viewModel::deleteAddress
        )
        binding.historyAddressList.adapter = adapter
        binding.historyAddressList.addItemDecoration(AddressAdapterDecoration())
    }

    private fun getHistoryPlaces() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    if (uiState.isLoading) {
                        showLoading()
                    } else {
                        adapter.submitList(uiState.items)
                        stopLoading()
                    }

                    uiState.message?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
                        viewModel.shownMessage()
                    }
                }
            }
        }
    }

    private fun showLoading() {
        binding.favoriteAddressLoading.visibility = View.VISIBLE
    }
    
    private fun stopLoading() {
        binding.favoriteAddressLoading.visibility = View.GONE
    }
}