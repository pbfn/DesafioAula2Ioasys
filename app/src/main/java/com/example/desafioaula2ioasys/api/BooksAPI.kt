package com.example.desafioaula2ioasys.api

import com.example.desafioaula2ioasys.domain.model.*
import retrofit2.Response
import retrofit2.http.*

interface BooksAPI {

    @Headers("Content-Type: application/json")
    @GET("books")
    suspend fun getBooks(
        @Header("Authorization") token: String,
        @Query("page")
        page: Int = 1,
        @Query("amount")
        amount: Int = 20
    ): Response<ListBooksResponse>

    @GET("books/{id}")
    suspend fun getBookById(): Response<BookResponse>

    @Headers("Content-type: application/json")
    @POST("auth/sign-in")
    suspend fun doLogin(
        @Body user: User
    ): Response<UserResponse>


}