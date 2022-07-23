package com.goldcompany.koreabike.domain.usecase

import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository
import javax.inject.Inject

class SearchAddressUseCase @Inject constructor(private val repository: KBikeRepository) {
    suspend operator fun invoke(address: String, page: Int): List<Address> = repository.searchAddress(address, page)
}