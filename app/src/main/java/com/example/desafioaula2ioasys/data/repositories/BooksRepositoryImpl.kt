package com.example.desafioaula2ioasys.data.repositories

import com.example.desafioaula2ioasys.data.datasource.local.BooksLocalDataSource
import com.example.desafioaula2ioasys.data.datasource.remote.BooksRemoteDataSource
import com.example.desafioaula2ioasys.domain.model.Book
import com.example.desafioaula2ioasys.domain.model.ListBooks
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class BooksRepositoryImpl(
    private val booksRemoteDataSource: BooksRemoteDataSource,
    private val booksLocalDataSource: BooksLocalDataSource
) : BooksRepository {

    override fun getBooks(page: Double, amount: Int): Flow<ListBooks> = flow {
        booksLocalDataSource.getAccessToken().collect { accestoken ->
            if (accestoken.isNotEmpty()) {
                booksRemoteDataSource.getBooks("Bearer $accestoken", page, amount)
                    .collect { bookList ->
                        emit(bookList)
                    }
            }
        }

    }

    override fun saveBooks(bookList: List<Book>) = booksLocalDataSource.saveBooks(
        bookList = bookList
    )

    override fun searchBooks(titleSearch: String?): Flow<ListBooks> = flow {
        booksLocalDataSource.getAccessToken().collect { accestoken ->
            if (accestoken.isNotEmpty()) {
                booksRemoteDataSource.searchBooks("Bearer $accestoken", titleSearch)
                    .collect { bookList ->
                        emit(bookList)
                    }
            }
        }
    }

}