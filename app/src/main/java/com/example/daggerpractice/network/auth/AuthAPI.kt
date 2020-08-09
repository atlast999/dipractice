package com.example.daggerpractice.network.auth

import com.example.daggerpractice.model.User
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthAPI {
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User?>
}