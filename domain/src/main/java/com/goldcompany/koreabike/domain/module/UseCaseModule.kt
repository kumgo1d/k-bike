package com.goldcompany.koreabike.domain.module

import com.goldcompany.koreabike.domain.repository.KBikeRepository
import com.goldcompany.koreabike.domain.usecase.GetAllHistoryAddressUseCase
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchAddressUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideSearchAddressUseCase(
        repository: KBikeRepository
    ): SearchAddressUseCase = SearchAddressUseCase(repository)

    @Singleton
    @Provides
    fun provideGetAllHistoryAddressUseCase(
        repository: KBikeRepository
    ): GetAllHistoryAddressUseCase = GetAllHistoryAddressUseCase(repository)

    @Singleton
    @Provides
    fun provideGetCurrentAddressUseCase(
        repository: KBikeRepository
    ): GetCurrentAddressUseCase = GetCurrentAddressUseCase(repository)
}