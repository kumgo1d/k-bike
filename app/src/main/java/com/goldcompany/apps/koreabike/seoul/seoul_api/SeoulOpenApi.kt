package com.goldcompany.apps.koreabike.seoul.seoul_api

import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

class SeoulOpenApi {
    companion object {
        const val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        const val API_KEY1 = "6e4e5458526a756e36304c6f78427a/json/bikeList/1/1000/"
        const val API_KEY2 = "6e4e5458526a756e36304c6f78427a/json/bikeList/1001/2000/"
        const val API_KEY3 = "6e4e5458526a756e36304c6f78427a/json/bikeList/2001/3000/"
    }
}

interface SeoulOpenService {
    @GET
    fun getBike(@Url url: String): Call<SeoulBike>
}