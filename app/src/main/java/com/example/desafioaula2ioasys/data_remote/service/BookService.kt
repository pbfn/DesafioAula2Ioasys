package com.example.desafioaula2ioasys.data_remote.service

import com.example.desafioaula2ioasys.data_remote.model.BooksListResponse
import com.example.desafioaula2ioasys.domain.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookService {

    @Headers("Content-Type: application/json")
    @GET("books")
    suspend fun getBooks(
        @Header("Authorization") token: String,
        @Query("page")
        page: Int = 1,
        @Query("amount")
        amount: Int = 20
    ): Response<BooksListResponse>

    @GET("books/{id}")
    suspend fun getBookById(): Response<Book>
}