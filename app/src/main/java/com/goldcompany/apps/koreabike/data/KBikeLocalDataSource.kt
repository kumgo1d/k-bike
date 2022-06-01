package com.goldcompany.apps.koreabike.data

import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddressDAO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KBikeLocalDataSource @Inject constructor(
    private val addressDao: UserHistoryAddressDAO
) {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun getAllAddress(): MutableList<UserHistoryAddress> = withContext(ioDispatcher) {
        return@withContext addressDao.getAll()
    }

    suspend fun getAddress(): UserHistoryAddress? = withContext(ioDispatcher) {
        return@withContext addressDao.getAddress()
    }

    suspend fun updateAddressUnselect(date: Long) = withContext(ioDispatcher) {
        addressDao.updateAddressUnselect(date)
    }

    suspend fun insertAddress(address: UserHistoryAddress) = withContext(ioDispatcher) {
        addressDao.insert(address)
    }

    suspend fun deleteAddress(address: UserHistoryAddress) = withContext(ioDispatcher) {
        addressDao.delete(address)
    }
}