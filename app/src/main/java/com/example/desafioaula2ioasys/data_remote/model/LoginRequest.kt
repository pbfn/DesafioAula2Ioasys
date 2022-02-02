package com.example.desafioaula2ioasys.data_remote.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String
)
