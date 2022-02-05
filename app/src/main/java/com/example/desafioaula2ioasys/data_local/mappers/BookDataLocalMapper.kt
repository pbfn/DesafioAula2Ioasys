package com.example.desafioaula2ioasys.data_local.mappers

import com.example.desafioaula2ioasys.data_local.model.BookDataLocal
import com.example.desafioaula2ioasys.domain.model.Book

fun Book.toDao(): BookDataLocal = BookDataLocal(
    id = this.id,
    authors = this.authors,
    category = this.category,
    description = this.description,
    imageUrl = this.imageUrl,
    isbn10 = this.isbn10,
    isbn13 = this.isbn13,
    language = this.language,
    pageCount = this.pageCount,
    published = this.published,
    publisher = this.publisher,
    title = this.title
)

fun BookDataLocal.toDomain() : Book = Book(
    id = this.id ?: "",
    authors = (this.authors ?: "") as List<String>,
    category = this.category ?: "",
    description = this.description ?: "",
    imageUrl = this.imageUrl ?: "",
    isbn10 = this.isbn10 ?: "",
    isbn13 = this.isbn13 ?: "",
    language = this.language ?: "",
    pageCount = this.pageCount ?: 0,
    published = this.published ?: 0,
    publisher = this.publisher ?: "",
    title = this.title ?: ""
)