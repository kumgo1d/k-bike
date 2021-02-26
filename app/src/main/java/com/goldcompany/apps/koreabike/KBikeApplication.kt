package com.goldcompany.apps.koreabike

import android.app.Application
import android.util.Log
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.db.repo.UserAddressRepository

class KBikeApplication: Application() {
    companion object {
        lateinit var instance: KBikeApplication
            private set
    }

    val database by lazy { KBikeDatabase.getInstance(this)}

    val userAddressRepository by lazy { UserAddressRepository(database.UserAddressDAO()) }

    init {
        instance = this
    }
}