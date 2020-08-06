package com.example.daggerpractice.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(application: Application,
                                           private val creators: MutableMap<Class<out AndroidViewModel>, Provider<AndroidViewModel>>)
    : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (AndroidViewModel::class.java.isAssignableFrom(modelClass)){
            creators.get(modelClass as Class<*>)?.get() as T?
                ?: throw RuntimeException(modelClass.simpleName + "is not added to binds-map, check VM module")
        } else{
            super.create(modelClass)
        }
    }
}