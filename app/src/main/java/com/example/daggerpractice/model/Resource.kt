package com.example.daggerpractice.model

import androidx.annotation.Nullable

data class Resource<T> private constructor(@Nullable val status: AuthStatus,
                                           @Nullable val data: T?,
                                           @Nullable val message: String?
) {
    companion object{
        fun <T> success(data: T) = Resource(AuthStatus.SUCCESS, data, null)
        fun <T> error(data: T, message: String?) = Resource(AuthStatus.ERROR, data, message)
        fun <T> loading() = Resource<T>(AuthStatus.LOADING, null, null)
        fun <T> logOut() = Resource<T>(AuthStatus.LOGOUT, null, null)
    }

    enum class AuthStatus {
        SUCCESS,
        ERROR,
        LOADING,
        LOGOUT
    }
}

