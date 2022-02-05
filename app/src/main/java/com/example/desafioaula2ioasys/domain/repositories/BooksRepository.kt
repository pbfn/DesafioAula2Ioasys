package com.example.desafioaula2ioasys.domain.repositories

import com.example.desafioaula2ioasys.domain.model.Book
import com.example.desafioaula2ioasys.domain.model.ListBooks
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    fun getBooks(page: Int, amount: Int, titleSearch: String): Flow<ListBooks>
    fun saveBooks(bookList: List<Book>)
}