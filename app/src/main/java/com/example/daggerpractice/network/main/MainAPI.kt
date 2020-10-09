package com.example.daggerpractice.network.main

import com.example.daggerpractice.model.Comment
import com.example.daggerpractice.model.Post
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainAPI {

    @GET("posts")
    fun getPostsFromUser(@Query("userId") id: Int?): Flowable<List<Post>>

    @GET("comments")
    fun getCommentsFromPost(@Query("postId") id: Int?): Single<List<Comment>>
}