package com.example.daggerpractice.di.module

import com.example.daggerpractice.di.annotation.AuthScope
import com.example.daggerpractice.di.annotation.MainScope
import com.example.daggerpractice.di.auth.AuthModule
import com.example.daggerpractice.di.auth.AuthViewModelsModule
import com.example.daggerpractice.di.main.MainFragmentProviderModule
import com.example.daggerpractice.di.main.MainModule
import com.example.daggerpractice.di.main.MainViewModelsModule
import com.example.daggerpractice.ui.auth.AuthActivity
import com.example.daggerpractice.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [
            AuthViewModelsModule::class,
            AuthModule::class
        ]
    )
    fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentProviderModule::class,
            MainViewModelsModule::class,
            MainModule::class
        ]
    )
    fun contributeMainActivity(): MainActivity


}