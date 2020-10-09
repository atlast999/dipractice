package com.example.daggerpractice.di.main

import androidx.lifecycle.AndroidViewModel
import com.example.daggerpractice.di.annotation.FragmentScope
import com.example.daggerpractice.di.annotation.ViewModelKey
import com.example.daggerpractice.ui.main.post.PostsViewModel
import com.example.daggerpractice.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainViewModelsModule {

//    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(viewModel: ProfileViewModel): AndroidViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    fun providePostsViewModel(viewModel: PostsViewModel): AndroidViewModel
}