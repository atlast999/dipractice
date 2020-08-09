package com.example.daggerpractice.base

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.model.Resource
import com.example.daggerpractice.ui.auth.AuthActivity
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        sessionManager.getCachedUser().observe(this, Observer {
            when(it.status){
                Resource.AuthStatus.SUCCESS -> Timber.i("Login successfully")
                Resource.AuthStatus.LOGOUT -> {
                    Timber.i("Logging out...")
                    navLoginScreen()
                }
                else -> Timber.i("Something wrong")
            }
        })
    }

    private fun navLoginScreen(){
        startActivity(Intent(this, AuthActivity::class.java))
    }
}