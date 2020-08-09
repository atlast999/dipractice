package com.example.daggerpractice.ui.auth

import android.app.Application
import androidx.lifecycle.*
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.model.Resource
import com.example.daggerpractice.model.User
import com.example.daggerpractice.network.auth.AuthAPI

import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class AuthViewModel @Inject constructor(application: Application,
                                        private val authAPI: AuthAPI,
                                        private val sessionManager: SessionManager) : AndroidViewModel(application) {

    fun authenticateWithId(userId: Int) {
        Timber.i("Attempting to login...")
        sessionManager.authenticateWithId(queryUserId(userId) as LiveData<Resource<User>>)
    }

    private fun queryUserId(userId:Int) = LiveDataReactiveStreams.fromPublisher(
        authAPI.getUser(userId)
            .subscribeOn(Schedulers.io())
            .onErrorReturn { User(-1, null, null, null) }
            .map { user ->
                if(user.id == -1){
                    Resource.error(null, "something wrong")
                } else{
                    Resource.success(user)
                }
            }
    )

    fun getLiveUser() = sessionManager.getCachedUser()
}