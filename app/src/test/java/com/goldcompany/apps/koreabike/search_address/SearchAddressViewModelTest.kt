package com.goldcompany.apps.koreabike.search_address

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.goldcompany.apps.koreabike.MainCoroutineRule
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.ui.search_address.SearchAddressViewModel
import com.goldcompany.apps.koreabike.util.Result
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

//robolectric 에러 방지를 위해 SDK 버전 명시
@Config(sdk = [Build.VERSION_CODES.O])
//코루틴 테스트를 위해 명시
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SearchAddressViewModelTest {

    //makes each task executed one after another
    //prevents asynchronous operations
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //default coroutine dispatcher is the viewModelScope
    //viewModelScope is the Dispatchers.Main and Dispatchers.Main uses
    //Android's Main Looper which is not available in the local test environment
    //So in order to write unit test for a viewModel that uses coroutines,
    //need to swap that dispatcher with a test dispatcher.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchAddressViewModel

    @MockK
    private lateinit var repository: KBikeRepository

    @Before
    fun setupViewModel() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = SearchAddressViewModel(repository)
    }

    @Test
    fun searchAddress_pagingDataLoaded() {
        mainCoroutineRule.runBlockingTest {
            val result = Result.Success(
                viewModel.searchAddress("현대백화점")
            )

            Assert.assertEquals("", result)
        }
    }
}