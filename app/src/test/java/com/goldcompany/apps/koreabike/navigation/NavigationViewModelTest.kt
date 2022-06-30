package com.goldcompany.apps.koreabike.navigation


import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.goldcompany.apps.koreabike.MainCoroutineRule
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddressDAO
import com.goldcompany.apps.koreabike.ui.navigation.NavigationViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O])
@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class NavigationViewModelTest {

    @get:Rule(order = 0)
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule(order = 1)
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var database: KBikeDatabase
    private lateinit var dao: UserHistoryAddressDAO

    @MockK
    private lateinit var repository: KBikeRepository
    @MockK
    private lateinit var viewModel: NavigationViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            KBikeDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.userAddressDAO()
        repository = mockk()
        viewModel = NavigationViewModel(repository)
    }

    @Test
    fun getNavigationPathTest() = runBlockingTest {
        val start = "126.976861018866,37.5759689663327"
        val end = "127.011795743342,37.2869569586225"
        val message = "길찾기를 성공하였습니다."

        every { runBlockingTest { repository.getNavigationPath(start, end) } } returns mockk()

        val result = viewModel.getNavigationPath(start, end).first()
        Assert.assertEquals(message, result.message)
    }
}