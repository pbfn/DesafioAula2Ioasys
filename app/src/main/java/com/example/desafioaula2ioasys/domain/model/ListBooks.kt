package com.example.desafioaula2ioasys.domain.model

data class ListBooks(
    val data: MutableList<Book>,
    val page: Int,
    val totalItems: Int,
    val totalPages: Double
)