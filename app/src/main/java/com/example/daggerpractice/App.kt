package com.example.daggerpractice

import com.example.daggerpractice.di.DaggerAppComponent
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App: DaggerApplication() {

    override fun applicationInjector() =
        DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { t -> t.cause }
        Timber.plant(Timber.DebugTree())
    }
}