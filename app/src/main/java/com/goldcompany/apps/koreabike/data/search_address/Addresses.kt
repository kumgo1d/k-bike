package com.goldcompany.apps.koreabike.data.search_address

import com.google.gson.annotations.SerializedName

data class Addresses(
    @SerializedName("documents") val addressList: List<AddressItem>,
    val meta: AddressMetaData
)