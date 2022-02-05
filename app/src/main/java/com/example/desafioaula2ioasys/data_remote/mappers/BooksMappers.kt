package com.example.desafioaula2ioasys.data_remote.mappers

import com.example.desafioaula2ioasys.data_remote.model.BookResponse
import com.example.desafioaula2ioasys.domain.model.Book

fun List<BookResponse>.toDomain(): MutableList<Book> {
    val books: MutableList<Book> = mutableListOf()
    for (book in this) {
        books.add(book.toDomain())
    }
    return books
}

fun BookResponse.toDomain(): Book = Book(
    authors,
    category,
    description,
    id,
    imageUrl,
    isbn10,
    isbn13,
    language,
    pageCount,
    published,
    publisher,
    title
)
