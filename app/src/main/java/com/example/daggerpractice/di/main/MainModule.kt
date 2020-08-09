package com.example.daggerpractice.di.main

import com.example.daggerpractice.di.annotation.MainScope
import com.example.daggerpractice.network.main.MainAPI
import com.example.daggerpractice.ui.main.post.PostsAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun providePostsAdapter() = PostsAdapter()

    @MainScope
    @Provides
    fun provideMainAPI(retrofit: Retrofit): MainAPI {
        return retrofit.create(MainAPI::class.java)
    }
}