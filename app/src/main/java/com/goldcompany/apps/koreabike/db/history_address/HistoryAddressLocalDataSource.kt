package com.goldcompany.apps.koreabike.db.history_address

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryAddressLocalDataSource internal constructor(
    private val addressDao: UserHistoryAddressDAO,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getAllAddress(): MutableList<UserHistoryAddress> = withContext(ioDispatcher) {
        return@withContext addressDao.getAll()
    }

    suspend fun getAddress(): UserHistoryAddress = withContext(ioDispatcher) {
        return@withContext addressDao.getAddress()
    }

    suspend fun insertAddress(address: UserHistoryAddress) = withContext(ioDispatcher) {
        addressDao.insert(address)
    }

    suspend fun deleteAddress(address: UserHistoryAddress) = withContext(ioDispatcher) {
        addressDao.delete(address)
    }

}