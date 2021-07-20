package com.goldcompany.apps.koreabike.ui.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.MainActivity
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.api.FindPlaces
import com.goldcompany.apps.koreabike.api.NaverApiRetrofitClient
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoAddressItem
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding
import com.goldcompany.apps.koreabike.databinding.SubSearchAddressItemBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    override fun onStop() {
        super.onStop()
        binding.start.clearFocus()
        binding.end.clearFocus()
        MainActivity.hideKeyboard(binding.root)
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
            findNavController().popBackStack()
        }

        binding.start.addTextChangedListener(textChangeListener())

        binding.end.addTextChangedListener(textChangeListener())

        binding.navigateButton.setOnClickListener {
            navigateApi()
        }
    }

    private fun textChangeListener() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            lifecycleScope.launch {
                delay(1000)
                searchAddress(s.toString())
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun searchAddress(address: String) {
        FindPlaces().callKakaoKeyword(address = address) { data, _ ->
            if(data == null) {
                //TODO 에러 처리
                return@callKakaoKeyword
            }

            binding.addressRecyclerView.adapter = NavigationAdapter(data)
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

class NavigationAdapter(private val dataSet: KakaoData): RecyclerView.Adapter<NavigationAdapter.ViewHolder>() {
    class ViewHolder(private val binding: SubSearchAddressItemBinding): RecyclerView.ViewHolder(binding.root) {
        val keyword = binding.itemKeyword
        val address = binding.itemAddress

        fun bind(item: KakaoAddressItem) {
            keyword.text = item.placeName
            address.text = item.addressName

            binding.root.setOnClickListener {
                //TODO 키워드 혹은 주소를 EditText에 전달. geocoding.

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SubSearchAddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet.addressList[position])
    }

    override fun getItemCount(): Int = dataSet.addressList.size
}