package com.android.apps.seoulpublicbike

import com.android.apps.seoulpublicbike.data.Bike
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class SeoulOpenApi {
    companion object {
        val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        val API_KEY = "6e4e5458526a756e36304c6f78427a"
    }
}

interface SeoulOpenService {
    @GET("{api_key}/json/bikeList/1/200")
    fun getBike(@Path("api_key") key: String): Call<Bike>
}