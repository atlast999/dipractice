package com.example.daggerpractice.di.auth

import androidx.lifecycle.AndroidViewModel
import com.example.daggerpractice.di.annotation.ViewModelKey
import com.example.daggerpractice.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun provideAuthViewModel(viewModel: AuthViewModel): AndroidViewModel
}