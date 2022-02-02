package com.example.desafioaula2ioasys.data_remote.service

import com.example.desafioaula2ioasys.data_remote.model.LoginRequest
import com.example.desafioaula2ioasys.data_remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-type: application/json")
    @POST("auth/sign-in")
    suspend fun doLogin(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}