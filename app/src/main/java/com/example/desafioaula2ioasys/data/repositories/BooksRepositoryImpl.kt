package com.example.desafioaula2ioasys.data.repositories

import com.example.desafioaula2ioasys.data.datasource.local.BooksLocalDataSource
import com.example.desafioaula2ioasys.data.datasource.remote.BooksRemoteDataSource
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class BooksRepositoryImpl(
    private val booksRemoteDataSource: BooksRemoteDataSource,
    private val booksLocalDataSource: BooksLocalDataSource
) : BooksRepository {

    override fun getBooks(page: Int, amount: Int): Flow<ListBooksResponse> = flow {
        booksLocalDataSource.getAccessToken().collect { accestoken ->
            booksRemoteDataSource.getBooks("Bearer $accestoken", page, amount).collect { bookList ->
                emit(bookList)
            }
        }

    }


}