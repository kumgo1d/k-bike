package com.goldcompany.apps.koreabike.seoul.seoul_api

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

object SeoulRepository {

    val bike1 = MutableLiveData<SeoulBike>()
    val bike2 = MutableLiveData<SeoulBike>()
    val bike3 = MutableLiveData<SeoulBike>()

    @SuppressLint("SimpleDateFormat")
    private val timestamp = SimpleDateFormat("yyyyMMddHH", Locale.KOREA)

    fun getBikeData1(): MutableLiveData<SeoulBike> {
        Log.i("timestamp", "${timestamp}")
        val call = SeoulMapRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY1 + timestamp).enqueue(object : Callback<SeoulBike> {
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
        val call = SeoulMapRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY2 + timestamp).enqueue(object : Callback<SeoulBike> {
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
        val call = SeoulMapRetrofitClient.seoulOpenService

        call.getBike(SeoulOpenApi.API_KEY3 + timestamp).enqueue(object : Callback<SeoulBike> {
            override fun onResponse(call: Call<SeoulBike>, response: Response<SeoulBike>) {
                bike3.value = response.body() as SeoulBike
            }

            override fun onFailure(call: Call<SeoulBike>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return bike3
    }
}