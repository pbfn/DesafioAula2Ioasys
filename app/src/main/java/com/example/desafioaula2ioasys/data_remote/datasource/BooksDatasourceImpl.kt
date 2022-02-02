package com.example.desafioaula2ioasys.data_remote.datasource

import com.example.desafioaula2ioasys.data_remote.service.BookService
import com.example.desafioaula2ioasys.data.datasource.remote.BooksRemoteDatasource
import com.example.desafioaula2ioasys.data_remote.mappers.toDomain
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class BooksDatasourceImpl(
    private val booksService: BookService
) : BooksRemoteDatasource {
    override fun getBooks(token: String, page: Int, amount: Int): Flow<ListBooksResponse> = flow {
        val response = booksService.getBooks(token,page, amount)
        if(response.isSuccessful){
            response.body()?.let {
                emit(it.toDomain())
            }
        }
    }
}