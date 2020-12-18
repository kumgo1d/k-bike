package com.android.apps.seoulpublicbike.seoul

import com.android.apps.seoulpublicbike.data.Bike
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object {
        const val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        const val API_KEY1 = "6e4e5458526a756e36304c6f78427a/json/bikeList/1/1000"
        const val API_KEY2 = "6e4e5458526a756e36304c6f78427a/json/bikeList/1001/2000"
        const val API_KEY3 = "6e4e5458526a756e36304c6f78427a/json/bikeList/2001/3000"
    }
}

interface SeoulOpenService {
    @GET("{api_key}")
    fun getBike(@Path("api_key") key: String): Call<Bike>
}