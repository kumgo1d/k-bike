package com.goldcompany.koreabike.domain.usecase

import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchAddressUseCase @Inject constructor(private val repository: KBikeRepository) {
    suspend operator fun invoke(address: String, page: Int):
            Result<List<Address>> = withContext(Dispatchers.IO) {
        return@withContext repository.searchAddress(address, page)
    }
}