package com.example.desafioaula2ioasys.data_remote.datasource

import com.example.desafioaula2ioasys.data_remote.service.BookService
import com.example.desafioaula2ioasys.data.datasource.remote.BooksRemoteDataSource
import com.example.desafioaula2ioasys.data_remote.mappers.toDomain
import com.example.desafioaula2ioasys.domain.model.ListBooks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class BooksDataSourceImpl(
    private val booksService: BookService
) : BooksRemoteDataSource {
    override fun getBooks(
        token: String,
        page: Double,
        amount: Int
    ): Flow<ListBooks> = flow {
        val response = booksService.getBooks(token, page, amount)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.toDomain())
            }
        }
    }

    override fun searchBooks(token: String, titleSearch: String?): Flow<ListBooks> = flow{
        val response = booksService.searchBooks(token = token,titleSearch = titleSearch)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.toDomain())
            }
        }
    }
}