package com.example.daggerpractice.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("username") @Expose val username: String?,
    @SerializedName("email") @Expose val email: String?,
    @SerializedName("website") @Expose val website: String?): Serializable