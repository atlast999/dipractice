package com.example.daggerpractice.di.auth

import com.example.daggerpractice.di.annotation.AuthScope
import com.example.daggerpractice.network.auth.AuthAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI{
        return retrofit.create(AuthAPI::class.java)
    }
}