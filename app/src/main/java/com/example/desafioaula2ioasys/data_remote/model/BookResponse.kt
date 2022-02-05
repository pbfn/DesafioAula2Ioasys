package com.example.desafioaula2ioasys.data_remote.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("authors")
    val authors: List<String>?=null,
    @SerializedName("category")
    val category: String?=null,
    @SerializedName("description")
    val description: String?=null,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String?=null,
    @SerializedName("isbn10")
    val isbn10: String?=null,
    @SerializedName("isbn13")
    val isbn13: String?=null,
    @SerializedName("language")
    val language: String?=null,
    @SerializedName("pageCount")
    val pageCount: Int?=null,
    @SerializedName("published")
    val published: Int?=null,
    @SerializedName("publisher")
    val publisher: String?=null,
    @SerializedName("title")
    val title: String?=null
)
