package com.goldcompany.apps.koreabike.api

import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.data.category_group.CategoryGroup
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FindPlaces {
    private val KAKAO_API_KEY = "KakaoAK 09ab5a332869126358f643b6ff26abc8"
    @Inject lateinit var kakaoApiService: KakaoApiService

    fun callKakaoKeyword(
        address: String,
        onComplete: (KakaoData?, Exception?) -> Unit
    ) {
        kakaoApiService = KakaoApiRetrofitClient_ProvideKakaoApiServiceFactory.provideKakaoApiService()
        kakaoApiService.getKakaoAddress(KAKAO_API_KEY, address = address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onComplete(it, null)
            }, {
                onComplete(null, NullPointerException(it.message))
            })
    }

    fun callKakaoCategoryGroupItem(code: String, longitude: String, latitude: String): MutableLiveData<CategoryGroup> {
        val kakao = MutableLiveData<CategoryGroup>()

        kakaoApiService = KakaoApiRetrofitClient_ProvideKakaoApiServiceFactory.provideKakaoApiService()
        kakaoApiService.getCategoryGroup(KAKAO_API_KEY, code = code, longitude = longitude, latitude = latitude, radius = 10000)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                kakao.value = it
            }, {
                Timber.e(it)
            })

        return kakao
    }
}