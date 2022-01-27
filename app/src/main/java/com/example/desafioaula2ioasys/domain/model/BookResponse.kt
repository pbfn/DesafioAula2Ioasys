package com.example.desafioaula2ioasys.domain.model

data class BookResponse(
    val authors: List<String>,
    val category: String,
    val description: String,
    val id: String,
    val imageUrl: String,
    val isbn10: String,
    val isbn13: String,
    val language: String,
    val pageCount: Int,
    val published: Int,
    val publisher: String,
    val title: String
)