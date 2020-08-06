package com.example.daggerpractice.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.model.User
import com.example.daggerpractice.network.auth.AuthAPI
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.Async
import timber.log.Timber
import javax.inject.Inject

class AuthViewModel @Inject constructor(application: Application,
                                        private val authAPI: AuthAPI) : AndroidViewModel(application) {

//    @Inject
//    lateinit var authAPI: AuthAPI
    fun getUser() {
    authAPI.getUser(1)
        .toObservable()
        .subscribeOn(Schedulers.io())
        .subscribe(object : Observer<User>{
            override fun onComplete() {
                TODO("Not yet implemented")
            }

            override fun onSubscribe(d: Disposable) {
                TODO("Not yet implemented")
            }

            override fun onNext(t: User) {
                Timber.i(t.username)
            }

            override fun onError(e: Throwable) {
                Timber.i(e)
            }

        })
}
}