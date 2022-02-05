package com.example.desafioaula2ioasys.domain.model

import com.example.desafioaula2ioasys.data_remote.model.BookResponse

data class ListBooks(
    val data: MutableList<Book>,
    val page: Int,
    val totalItems: Int,
    val totalPages: Int
)