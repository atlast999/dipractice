package com.example.daggerpractice.ui.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.daggerpractice.SessionManager
import javax.inject.Inject

class ProfileViewModel @Inject constructor(application: Application,
                                           private val sessionManager: SessionManager) : AndroidViewModel(application) {



    fun getLiveUser() = sessionManager.getCachedUser()
}