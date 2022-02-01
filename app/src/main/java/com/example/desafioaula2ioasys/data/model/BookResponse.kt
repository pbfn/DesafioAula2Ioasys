package com.example.desafioaula2ioasys.data.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("authors")
    val authors: List<String>,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("isbn10")
    val isbn10: String,
    @SerializedName("isbn13")
    val isbn13: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("published")
    val published: Int,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("title")
    val title: String
)
