package com.agoda.sneakershop

import android.app.Application
import com.agoda.sneakershop.di.AppComponent
import com.agoda.sneakershop.di.AppModule
import com.agoda.sneakershop.di.DaggerAppComponent
import rx.plugins.RxJavaHooks
import rx.plugins.RxJavaPlugins
import rx.plugins.SimpleDebugNotificationListener


class SneakerShopApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule())
                .build()
    }
}