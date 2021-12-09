package com.goldcompany.apps.koreabike.ui.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.Constants
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentWebContainerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebContainerFragment : Fragment() {
    private lateinit var binding: FragmentWebContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_web_container, container, false)
        binding.fragment = this

        MainActivity.instance.showBottom()

        return binding.root
    }

    fun onButtonClick(view: View) {
        when(view) {
            binding.seoulButton.button -> {
                browseWebPage(Constants.SEOUL)
            }
            binding.daejeonButton.button -> {
                browseWebPage(Constants.DAEJEON)
            }
            binding.changwonButton.button -> {
                browseWebPage(Constants.CHANGWON)
            }
            binding.gwangjuButton.button -> {
                browseWebPage(Constants.GWANGJU)
            }
        }
    }

    private fun browseWebPage(url: String) {
        findNavController().navigate(WebContainerFragmentDirections.actionWebViewContainerToWebView(url))
    }
}