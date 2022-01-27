package com.example.desafioaula2ioasys.domain.model

data class ListBooksResponse(
    val data: MutableList<BookResponse>,
    val page: Int,
    val totalItems: Int,
    val totalPages: Int
)