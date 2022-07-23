package com.goldcompany.koreabike.data.mapper

import com.goldcompany.koreabike.data.db.AddressEntity
import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.domain.model.Address

fun mapperApiAddressToAddress(apiAddress: ApiAddress): Address {
    return Address(
        id = apiAddress.id,
        addressName = apiAddress.addressName,
        roadAddressName = apiAddress.roadAddressName,
        categoryName = apiAddress.categoryName,
        phone = apiAddress.phone,
        placeName = apiAddress.placeName,
        placeUrl = apiAddress.placeUrl,
        x = apiAddress.x,
        y = apiAddress.y
    )
}

fun mapperUserAddressEntityToAddress(entity: AddressEntity): Address {
    return Address(
        id = entity.id,
        addressName = entity.address,
        roadAddressName = entity.roadAddress,
        categoryName = entity.category,
        phone = entity.phone,
        placeUrl = entity.placeUrl,
        placeName = entity.placeName,
        x = entity.latitude.toString(),
        y = entity.longitude.toString()
    )
}

fun mapperAddressToUserAddressEntity(address: Address): AddressEntity {
    return AddressEntity(
        selected = true,
        id = address.id,
        address = address.addressName,
        roadAddress = address.roadAddressName,
        category = address.categoryName,
        phone = address.phone,
        placeName = address.placeName,
        placeUrl = address.placeUrl,
        latitude = address.x.toDouble(),
        longitude = address.y.toDouble()
    )
}