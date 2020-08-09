package com.example.daggerpractice.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.daggerpractice.R
import com.example.daggerpractice.databinding.ActivityAuthBinding
import com.example.daggerpractice.model.Resource
import com.example.daggerpractice.network.auth.AuthAPI
import com.example.daggerpractice.ui.main.MainActivity
import com.example.daggerpractice.vm.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var logo: Drawable
    @Inject
    lateinit var requestManager: RequestManager


    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)

        binding.loginButton.setOnClickListener { attemptLogin() }

        requestManager.load(logo).into(binding.loginLogo)

        subscribeObservers()

        Timber.i(viewModelFactory.toString())
    }

    private fun subscribeObservers() {
        viewModel.getLiveUser().observe(this, Observer {
            when(it.status){
                Resource.AuthStatus.ERROR -> Timber.i(it.message)
                Resource.AuthStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    onLoginSuccess()
                }
                Resource.AuthStatus.LOADING -> binding.progressBar.visibility = View.VISIBLE
                Resource.AuthStatus.LOGOUT -> Timber.i("Logged out...")
            }
        })
    }

    private fun onLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun attemptLogin(){
        if (TextUtils.isEmpty(binding.userIdInput.text)){
            return
        }
        viewModel.authenticateWithId(binding.userIdInput.text.toString().toInt())
    }
}