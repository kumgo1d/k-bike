package com.goldcompany.koreabike.data.repository.local

import com.goldcompany.koreabike.data.db.AddressDAO
import com.goldcompany.koreabike.data.db.AddressEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
interface KBikeLocalDataSource {
    suspend fun getAllAddress(): List<AddressEntity>

    suspend fun getAddress(): AddressEntity?

    suspend fun updateCurrentAddressUnselected(id: String)

    suspend fun insertAddress(address: AddressEntity)

    suspend fun deleteAddress(address: AddressEntity)
}

class KBikeLocalDataSourceImpl(private val addressDAO: AddressDAO): KBikeLocalDataSource {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getAllAddress(): List<AddressEntity> = withContext(ioDispatcher) {
        return@withContext addressDAO.getAll()
    }

    override suspend fun getAddress(): AddressEntity? = withContext(ioDispatcher) {
        return@withContext addressDAO.getAddress()
    }

    override suspend fun updateCurrentAddressUnselected(id: String) = withContext(ioDispatcher) {
        addressDAO.updateCurrentAddressUnselected(id)
    }

    override suspend fun insertAddress(address: AddressEntity) {
        addressDAO.insert(address)
    }

    override suspend fun deleteAddress(address: AddressEntity) {
        addressDAO.delete(address)
    }

}