package com.android.apps.seoulpublicbike

import androidx.lifecycle.MutableLiveData
import com.android.apps.seoulpublicbike.data.Bike
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SeoulRepository {

    val bike = MutableLiveData<Bike>()

    fun getBikeData(): MutableLiveData<Bike> {
        val call = SeoulMapRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY1).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                bike.value = response.body() as Bike
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        call.getBike(SeoulOpenApi.API_KEY2).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                bike.value = response.body() as Bike
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        call.getBike(SeoulOpenApi.API_KEY3).enqueue(object : Callback<Bike> {
            override fun onResponse(call: Call<Bike>, response: Response<Bike>) {
                bike.value = response.body() as Bike
            }

            override fun onFailure(call: Call<Bike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return bike
    }
}