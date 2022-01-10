package com.example.desafioaula2ioasys.api

import com.example.desafioaula2ioasys.models.BookResponse
import com.example.desafioaula2ioasys.models.ListBooksResponse
import com.example.desafioaula2ioasys.models.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BooksAPI {

    @GET("books")
    suspend fun getBooks():Response<ListBooksResponse>

    @GET("books/{id}")
    suspend fun getBookById():Response<BookResponse>

    @POST("auth/sign-in")
    suspend fun doLogin(
        @Query("email") email:String,
        @Query("password") password:String
    ):Response<LoginResponse>



}