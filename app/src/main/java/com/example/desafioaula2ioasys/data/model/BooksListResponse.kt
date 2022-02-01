package com.example.desafioaula2ioasys.data.model

import com.example.desafioaula2ioasys.domain.model.BookResponse
import com.google.gson.annotations.SerializedName

data class BooksListResponse(
    @SerializedName("data")
    val data: MutableList<BookResponse>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)

