package com.goldcompany.koreabike.data.model.address

import com.google.gson.annotations.SerializedName

data class ApiAddressResult(
    @SerializedName("documents") val addressList: List<ApiAddress>,
    val meta: AddressMetaData
)