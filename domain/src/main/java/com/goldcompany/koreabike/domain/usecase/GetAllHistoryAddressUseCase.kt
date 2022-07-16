package com.goldcompany.koreabike.domain.usecase

import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository

class GetAllHistoryAddressUseCase(private val repository: KBikeRepository) {
    suspend fun excute(): List<Address> = repository.getAllAddress()
}