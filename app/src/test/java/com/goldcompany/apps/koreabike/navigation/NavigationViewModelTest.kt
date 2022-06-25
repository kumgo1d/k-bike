package com.goldcompany.apps.koreabike.navigation


import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.goldcompany.apps.koreabike.MainCoroutineRule
import com.goldcompany.apps.koreabike.api.KakaoApiService
import com.goldcompany.apps.koreabike.api.NaverApiService
import com.goldcompany.apps.koreabike.data.KBikeLocalDataSource
import com.goldcompany.apps.koreabike.data.KBikeRemoteDataSource
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.di.NetworkModule
import com.goldcompany.apps.koreabike.ui.navigation.NavigationViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
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
import javax.inject.Inject

@Config(sdk = [Build.VERSION_CODES.O])
@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class NavigationViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var repository: KBikeRepository
    @MockK
    private lateinit var viewModel: NavigationViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = NavigationViewModel(repository)
    }

    @Test
    fun testMethod() {
        Assert.assertEquals(10, 10)
    }

    @Test
    fun getNavigationPathTest() = runBlockingTest {
        val start = "126.976861018866,37.5759689663327"
        val end = "127.011795743342,37.2869569586225"
        val message = "길찾기를 성공하였습니다."

        val result = viewModel.getNavigationPath(start, end).first()
        Assert.assertEquals(message, result.message)
    }
}