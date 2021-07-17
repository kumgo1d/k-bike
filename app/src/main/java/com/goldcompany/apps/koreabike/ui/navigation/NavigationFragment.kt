package com.goldcompany.apps.koreabike.ui.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
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
import java.util.ArrayList

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

    //TODO : 내비게이션 시작 좌표, 도착 좌표 지오코딩
    private fun navigateApi() {
        val navigation = NaverApiRetrofitClient.naverApi.getPath(
            NAVER_API_KEY_ID,
            NAVER_API_KEY,
            "126.9864,37.5610",
            "126.9725,37.5532",
            "tracomfort"
        )

        //TODO : 새로운 지도 Fragment에 마커표시
        navigation.enqueue(object : Callback<ResultPath> {
            override fun onResponse(call: Call<ResultPath>, response: Response<ResultPath>) {
                if(response.isSuccessful) {
                    Timber.d("body : ${response.body()}")

                    val path = response.body()!!.route.traComfort[0].path
                    val bundle = Bundle()

                    bundle.putParcelableArrayList("path", path as ArrayList<out Parcelable>)
                    findNavController().navigate(R.id.action_navigationFragment_to_navigationMapFragment, bundle)
                }
            }

            override fun onFailure(call: Call<ResultPath>, t: Throwable) {
                Timber.e("error : ${t.printStackTrace()}")
            }
        })
    }
}