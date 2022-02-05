package com.example.desafioaula2ioasys.data_remote.model

import com.example.desafioaula2ioasys.domain.model.Book
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

