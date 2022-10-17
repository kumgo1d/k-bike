package com.goldcompany.koreabike.domain.usecase

import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHistoryAddressUseCase @Inject constructor(private val repository: KBikeRepository) {
    suspend operator fun invoke(): Flow<List<Address>> = repository.getAllAddress()
}