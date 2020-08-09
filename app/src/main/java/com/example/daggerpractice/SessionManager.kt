package com.example.daggerpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.daggerpractice.model.Resource
import com.example.daggerpractice.model.User
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager @Inject constructor(){
    private val cachedUser = MediatorLiveData<Resource<User>>()

    fun authenticateWithId(source: LiveData<Resource<User>>) {
        cachedUser.value = Resource.loading()
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
        Timber.i("Previous session detected. Retrieving user from cache...")
    }

    fun logOut() {
        cachedUser.value = Resource.logOut()
        Timber.i("Logging out...")
    }

    fun getCachedUser() = cachedUser
}