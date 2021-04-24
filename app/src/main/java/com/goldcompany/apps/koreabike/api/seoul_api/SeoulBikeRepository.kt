package com.goldcompany.apps.koreabike.api.seoul_api

import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.data.CategoryGroup.CategoryGroup
import com.goldcompany.apps.koreabike.api.FindPlaces
import com.goldcompany.apps.koreabike.data.seoul.SeoulBike
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SeoulBikeRepository {

    val bike1 = MutableLiveData<SeoulBike>()
    val bike2 = MutableLiveData<SeoulBike>()
    val bike3 = MutableLiveData<SeoulBike>()

    fun getBikeData1(): MutableLiveData<SeoulBike> {
        val call = SeoulBikeRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY1).enqueue(object : Callback<SeoulBike> {
            override fun onResponse(call: Call<SeoulBike>, response: Response<SeoulBike>) {
                bike1.value = response.body() as SeoulBike
            }

            override fun onFailure(call: Call<SeoulBike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return bike1
    }

    fun getBikeData2(): MutableLiveData<SeoulBike> {
        val call = SeoulBikeRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY2).enqueue(object : Callback<SeoulBike> {
            override fun onResponse(call: Call<SeoulBike>, response: Response<SeoulBike>) {
                bike2.value = response.body() as SeoulBike
            }

            override fun onFailure(call: Call<SeoulBike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return bike2
    }

    fun getBikeData3(): MutableLiveData<SeoulBike> {
        val call = SeoulBikeRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY3).enqueue(object : Callback<SeoulBike> {
            override fun onResponse(call: Call<SeoulBike>, response: Response<SeoulBike>) {
                bike3.value = response.body() as SeoulBike
            }

            override fun onFailure(call: Call<SeoulBike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return bike3
    }

    fun getCategoryItem(code: String, longitude: String, latitude: String): MutableLiveData<CategoryGroup> {
        return FindPlaces().callKakaoCategoryGroupItem(code, longitude, latitude)
    }
}