package com.goldcompany.apps.koreabike.di

import android.content.Context
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddressDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): KBikeDatabase {
        return KBikeDatabase.getInstance(context)
    }

    @Provides
    fun provideUserHistoryDao(kBikeDatabase: KBikeDatabase): UserHistoryAddressDAO {
        return kBikeDatabase.userAddressDAO()
    }
}