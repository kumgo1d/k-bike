package com.goldcompany.apps.koreabike.data.kakaodata

import com.google.gson.annotations.SerializedName

data class KakaoData(
    @SerializedName("documents") val addressList: List<KakaoAddressItem>,
    val meta: Meta
)