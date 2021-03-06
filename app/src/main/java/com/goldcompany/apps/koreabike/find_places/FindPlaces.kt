package com.goldcompany.apps.koreabike.find_places

import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.find_places.CategoryGroup.CategoryGroup
import com.goldcompany.apps.koreabike.find_places.kakaoapi.KakaoApi
import com.goldcompany.apps.koreabike.find_places.kakaoapi.KakaoApiRetrofitClient
import com.goldcompany.apps.koreabike.find_places.kakaodata.KakaoData
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import java.lang.NullPointerException

class FindPlaces {
    private val kakaoApi = KakaoApiRetrofitClient.apiService

    fun callKakaoKeyword(
        address: String,
        onComplete: (KakaoData?, Exception?) -> Unit
    ) {
        val kakao = MutableLiveData<KakaoData>()

        kakaoApi.getKakaoAddress(KakaoApi.API_KEY, address = address)
            .enqueue(object : retrofit2.Callback<KakaoData> {
                override fun onResponse(call: Call<KakaoData>, response: Response<KakaoData>) {
                    kakao.value = response.body()

                    onComplete(kakao.value, null)
                }

                override fun onFailure(call: Call<KakaoData>, t: Throwable) {
                    onComplete(null, NullPointerException(t.message))
                }
            })
    }

    fun callKakaoCategoryGroupItem(code: String, longitude: String, latitude: String): MutableLiveData<CategoryGroup> {
        val kakao = MutableLiveData<CategoryGroup>()

        kakaoApi.getCategoryGroup(KakaoApi.API_KEY, code = code, longitude = longitude, latitude = latitude, radius = 10000)
            .enqueue(object : retrofit2.Callback<CategoryGroup> {
                override fun onResponse(
                    call: Call<CategoryGroup>,
                    response: Response<CategoryGroup>
                ) {
                    kakao.value = response.body()
                }

                override fun onFailure(call: Call<CategoryGroup>, t: Throwable) {

                }
            })

        return kakao
    }
}