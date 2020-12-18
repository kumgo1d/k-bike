package com.android.apps.seoulpublicbike.seoul

import androidx.lifecycle.MutableLiveData
import com.android.apps.seoulpublicbike.data.Bike
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SeoulRepository {

    val bike1 = MutableLiveData<Bike>()
    val bike2 = MutableLiveData<Bike>()
    val bike3 = MutableLiveData<Bike>()

    fun getBikeData1(): MutableLiveData<Bike> {
        val call = SeoulMapRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY1).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                bike1.value = response.body() as Bike
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return bike1
    }
    fun getBikeData2(): MutableLiveData<Bike> {
        val call = SeoulMapRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY2).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                bike2.value = response.body() as Bike
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return bike2
    }
    fun getBikeData3(): MutableLiveData<Bike> {
        val call = SeoulMapRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY3).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                bike3.value = response.body() as Bike
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return bike3
    }
}