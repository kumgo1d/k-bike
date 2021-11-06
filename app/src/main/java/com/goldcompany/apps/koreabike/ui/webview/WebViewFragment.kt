package com.goldcompany.apps.koreabike.ui.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebView
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

        MainActivity.instance.hideBottom()
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
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)

                    binding.progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    binding.progressBar.visibility = View.GONE
                }
            }

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