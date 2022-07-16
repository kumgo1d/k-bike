package com.goldcompany.apps.koreabike.di

import com.goldcompany.apps.koreabike.api.KakaoApiService
import com.goldcompany.apps.koreabike.api.NaverApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideKakaoApiService(): KakaoApiService {
        return KakaoApiService.create()
    }

    @Singleton
    @Provides
    fun provideNaverApiService(): NaverApiService {
        return NaverApiService.create()
    }
}