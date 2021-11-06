package com.goldcompany.apps.koreabike.db.history_address

import com.goldcompany.apps.koreabike.db.LocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryAddressLocalDataSource internal constructor(
    private val addressDao: UserHistoryAddressDAO,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalDataSource {
    override suspend fun getAllAddress(): MutableList<UserHistoryAddress> = withContext(ioDispatcher) {
        return@withContext addressDao.getAll()
    }
}