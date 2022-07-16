package com.goldcompany.apps.koreabike.data.search_address

import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.data.model.address.AddressMetaData
import com.google.gson.annotations.SerializedName

data class Addresses(
    @SerializedName("documents") val addressList: List<ApiAddress>,
    val meta: AddressMetaData
)