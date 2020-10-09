package com.example.daggerpractice.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.daggerpractice.di.annotation.AuthScope
import com.example.daggerpractice.vm.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelProviderModule {

//    @AuthScope
    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.AndroidViewModelFactory

//    @ViewModelKey(AuthViewModel::class)
//    @IntoMap
//    @Binds
//    abstract fun provideAuthViewModel(viewModel: AuthViewModel): AndroidViewModel
}