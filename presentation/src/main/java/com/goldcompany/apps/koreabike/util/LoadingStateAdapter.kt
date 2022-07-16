package com.goldcompany.apps.koreabike.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.ItemNetworkStateBinding

class LoadingStateAdapter(
    private val retryCallback: () -> Unit
) : LoadStateAdapter<LoadingStateAdapter.NetworkStateItemViewHolder>() {

    inner class NetworkStateItemViewHolder(
        private val binding: ItemNetworkStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retryCallback }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState !is LoadState.Loading
                errorMessage.isVisible = loadState is LoadState.Error
            }
        }
    }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) = holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder = NetworkStateItemViewHolder(
        ItemNetworkStateBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_network_state, parent, false)
        )
    )
}
