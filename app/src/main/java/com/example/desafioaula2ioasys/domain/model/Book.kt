package com.example.desafioaula2ioasys.domain.model

data class Book(
    val authors: List<String>?=null,
    val category: String?=null,
    val description: String?=null,
    val id: String,
    val imageUrl: String?=null,
    val isbn10: String?=null,
    val isbn13: String?=null,
    val language: String?=null,
    val pageCount: Int?=null,
    val published: Int?=null,
    val publisher: String?=null,
    val title: String?=null,
)