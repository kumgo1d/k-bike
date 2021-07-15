package com.goldcompany.apps.koreabike.ui.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.api.NaverApiRetrofitClient
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class NavigationFragment : Fragment() {
    private lateinit var binding: FragmentNavigationBinding

    private val NAVER_API_KEY_ID = "fe7iwsbkl5"
    private val NAVER_API_KEY = "1KYsy93nxRaNmfxdHExFfyAIX89B8sfwePQw7bNP"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_navigation, container, false)

        setListener()

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListener() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            binding.start.clearFocus()
            binding.end.clearFocus()
            MainActivity.hideKeyboard(binding.root)
            return@setOnTouchListener true
        }

        binding.navigationAppBar.navigationBackButton.setOnClickListener {
            binding.start.clearFocus()
            binding.end.clearFocus()
            findNavController().popBackStack()
        }

        binding.navigateButton.setOnClickListener {
            navigateApi()
        }
    }

    private fun navigateApi() {
        val navigation = NaverApiRetrofitClient.naverApi.getPath(
            NAVER_API_KEY_ID,
            NAVER_API_KEY,
            "129.089441,35.231100",
            "129.084454,35.228982"
        )

        navigation.enqueue(object : Callback<ResultPath> {
            override fun onResponse(call: Call<ResultPath>, response: Response<ResultPath>) {
                Timber.d("error body : ${response.errorBody().toString()}")
                if(response.isSuccessful) {
                    Timber.d("body : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ResultPath>, t: Throwable) {
                Timber.e("error : ${t.printStackTrace()}")
            }
        })
    }
}