package com.android.realestatete

import android.app.Application
import com.android.realestatete.di.appModule
import com.android.realestatete.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class RealEstateTestAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RealEstateTestAndroidApp)
            modules(appModule, viewModelModule)
        }
    }
}