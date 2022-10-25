package com.goldcompany.koreabike.domain.usecase

import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.repository.KBikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchAddressUseCase @Inject constructor(private val repository: KBikeRepository) {
    operator fun invoke(address: String, page: Int): Flow<Result<List<Address>>> =
        repository.searchAddress(address, page)
}