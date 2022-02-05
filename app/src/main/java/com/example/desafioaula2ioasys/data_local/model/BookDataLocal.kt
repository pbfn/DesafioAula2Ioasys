package com.example.desafioaula2ioasys.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Books")
data class BookDataLocal(
    @PrimaryKey
    val id: String,
    val authors: List<String>? = null,
    val category: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val isbn10: String? = null,
    val isbn13: String? = null,
    val language: String? = null,
    val pageCount: Int? = null,
    val published: Int? = null,
    val publisher: String? = null,
    val title: String? = null
)