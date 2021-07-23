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
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
    private lateinit var viewModel: NavigationViewModel

    private val NAVER_API_KEY_ID = "fe7iwsbkl5"
    private val NAVER_API_KEY = "1KYsy93nxRaNmfxdHExFfyAIX89B8sfwePQw7bNP"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_navigation, container, false)
        viewModel = ViewModelProviders.of(this).get(NavigationViewModel::class.java)

        MainActivity.hideBottom()
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

        bindNavAddress()
        searchNavAddress()
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
        binding.start.addTextChangedListener(textChangeListener(true))
        binding.end.addTextChangedListener(textChangeListener(false))

        binding.navigateButton.setOnClickListener {
            navigateApi()
        }
    }

    private fun textChangeListener(isStart: Boolean) = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            lifecycleScope.launch {
                delay(1000)
                searchAddress(s.toString(), isStart)
            }
        }
    }

    private fun searchAddress(address: String, isStart: Boolean) {
        FindPlaces().callKakaoKeyword(address = address) { data, _ ->
            if(data == null) {
                Toast.makeText(requireContext(), "에러가 발생하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                return@callKakaoKeyword
            }

            binding.addressRecyclerView.adapter = NavigationAdapter(data, viewModel, isStart)
        }
    }

    private fun navigateApi() {
        if(viewModel.startX.isEmpty() || viewModel.endX.isEmpty()) {
            Toast.makeText(requireContext(), "주소를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val navigation = NaverApiRetrofitClient.naverApi.getPath(
            NAVER_API_KEY_ID,
            NAVER_API_KEY,
            "${viewModel.startX},${viewModel.startY}",
            "${viewModel.endX},${viewModel.endY}",
            "tracomfort"
        )

        navigation.enqueue(object : Callback<ResultPath> {
            override fun onResponse(call: Call<ResultPath>, response: Response<ResultPath>) {
                if(response.isSuccessful) {
//                    Timber.d("body : ${response.body()}")
                    val path = response.body()!!.route.traComfort[0].path
                    val duration = response.body()!!.route.traComfort[0].summary.duration
                    val distance = response.body()!!.route.traComfort[0].summary.distance
                    val bundle = Bundle()

                    bundle.putInt("duration", duration)
                    bundle.putInt("distance", distance)
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

class NavigationAdapter(private val dataSet: KakaoData,
                        private val viewModel: NavigationViewModel,
                        private val isStart: Boolean): RecyclerView.Adapter<NavigationAdapter.ViewHolder>() {
    class ViewHolder(private val binding: SubSearchAddressItemBinding): RecyclerView.ViewHolder(binding.root) {
        val keyword = binding.itemKeyword
        val address = binding.itemAddress

        fun bind(item: KakaoAddressItem, viewModel: NavigationViewModel, isStart: Boolean) {
            keyword.text = item.placeName
            address.text = item.addressName

            binding.root.setOnClickListener {
                if(isStart) {
                    viewModel.startAddress.value = item.placeName
                    viewModel.startX = item.x
                    viewModel.startY = item.y
                } else {
                    viewModel.endAddress.value = item.placeName
                    viewModel.endX = item.x
                    viewModel.endY = item.y
                }

                MainActivity.hideKeyboard(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SubSearchAddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet.addressList[position], viewModel, isStart)
    }

    override fun getItemCount(): Int = dataSet.addressList.size
}