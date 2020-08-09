package com.example.daggerpractice.di.main

import com.example.daggerpractice.di.annotation.AuthScope
import com.example.daggerpractice.ui.main.post.PostsFragment
import com.example.daggerpractice.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainFragmentProviderModule {

    @ContributesAndroidInjector
    fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    fun contributePostsFragment(): PostsFragment
}