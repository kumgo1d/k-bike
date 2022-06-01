package com.goldcompany.apps.koreabike.search_address

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.ui.search_address.SearchAddressViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SearchAddressViewModelTest {
    private lateinit var searchAddressViewModel: SearchAddressViewModel
    private lateinit var kBikeRepository: KBikeRepository

    @Before
    fun setupViewModel() {
        kBikeRepository
    }
}