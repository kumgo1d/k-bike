package com.goldcompany.apps.koreabike

import android.app.Application
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class KBikeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}