package com.goldcompany.apps.koreabike.navigation

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.goldcompany.apps.koreabike.MainCoroutineRule
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.ui.navigation.NavigationViewModel
import com.goldcompany.apps.koreabike.util.Result
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(sdk = [Build.VERSION_CODES.O], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NavigationViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var viewModel: NavigationViewModel

    @MockK
    lateinit var repository: KBikeRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        hiltRule.inject()
        viewModel = NavigationViewModel(repository)
    }

    @Test
    fun getNavigationPath_routeNullCheck() = runBlockingTest {
        val start = "126.976861018866,37.5759689663327"
        val end = "127.011795743342,37.2869569586225"
        val message = "길찾기를 성공하였습니다."

        val result = viewModel.getNavigationPath(start, end).first()
        Assert.assertEquals(message, result.message)
    }
}