package com.goldcompany.apps.koreabike.ui.search_address

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchAddressViewModelTest {

    @Test
    suspend fun setAddress() {
        val viewModel = SearchAddressViewModel(ApplicationProvider.getApplicationContext())
        assertNotNull(viewModel.searchAddress("서울"))
    }
}