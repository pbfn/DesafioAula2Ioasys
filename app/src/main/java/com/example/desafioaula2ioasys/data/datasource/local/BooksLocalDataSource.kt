package com.example.desafioaula2ioasys.data.datasource.local

import com.example.desafioaula2ioasys.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BooksLocalDataSource {
    fun getAccessToken(): Flow<String>
    fun saveBooks(bookList: List<Book>)
    fun getBooks():Flow<List<Book>>
}