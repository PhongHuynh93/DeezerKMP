package com.wind.deezerkmp.androidApp

import android.app.Application
import com.wind.deezerkmp.androidApp.di.appModule
import com.wind.deezerkmp.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber

/**
 * Created by Phong Huynh on 11/4/2020
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}