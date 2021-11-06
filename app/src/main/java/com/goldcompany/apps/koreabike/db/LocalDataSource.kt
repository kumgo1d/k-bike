package com.goldcompany.apps.koreabike.db


interface LocalDataSource {
    suspend fun getAllAddress(): MutableList<*>
}