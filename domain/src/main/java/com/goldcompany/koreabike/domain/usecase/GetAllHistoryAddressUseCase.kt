package com.goldcompany.koreabike.domain.usecase

import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository
import javax.inject.Inject

class GetAllHistoryAddressUseCase @Inject constructor(private val repository: KBikeRepository) {
    suspend operator fun invoke(): List<Address> = repository.getAllAddress()
}