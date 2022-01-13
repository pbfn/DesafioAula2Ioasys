package com.example.desafioaula2ioasys.api

import com.example.desafioaula2ioasys.models.*
import retrofit2.Response
import retrofit2.http.*

interface BooksAPI {

    @GET("books")
    suspend fun getBooks():Response<ListBooksResponse>

    @GET("books/{id}")
    suspend fun getBookById():Response<BookResponse>

    @Headers("Content-type: application/json")
    @POST("auth/sign-in")
    suspend fun doLogin(
       @Body user: User
    ):Response<UserResponse>



}