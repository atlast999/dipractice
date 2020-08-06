package com.example.daggerpractice.di

import android.app.Application
import com.example.daggerpractice.App
import com.example.daggerpractice.di.module.ActivityBuildersModule
import com.example.daggerpractice.di.module.AppModule
import com.example.daggerpractice.di.module.ViewModelProviderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class,
    ViewModelProviderModule::class]
)
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}