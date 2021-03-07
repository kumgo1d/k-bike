package com.goldcompany.apps.koreabike.webview

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
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false)

        webViewSetting()

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetting() {
        val web = binding.webView
        web.apply {
            loadUrl("https://www.bikeseoul.com/")
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()

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
            zoomIn()
            zoomOut()
        }

        web.settings.apply {
            javaScriptEnabled = true
        }
    }
}