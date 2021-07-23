package com.goldcompany.apps.koreabike.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false)

        MainActivity.hideBottom()
        webViewSetting()

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetting() {
        val web = binding.webView

        web.settings.apply {
            javaScriptEnabled = true
        }

        web.apply {
            loadUrl(WebViewFragmentArgs.fromBundle(requireArguments()).url)
            webViewClient = WebViewClient()

            webChromeClient = object : WebChromeClient() {
                override fun onGeolocationPermissionsShowPrompt(
                    origin: String?,
                    callback: GeolocationPermissions.Callback?
                ) {
                    super.onGeolocationPermissionsShowPrompt(origin, callback)
                    callback?.invoke(origin, true, false)
                }
            }

            canGoBack()
            canGoForward()
            zoomIn()
            zoomOut()
        }
    }
}