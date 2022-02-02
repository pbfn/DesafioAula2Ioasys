package com.example.desafioaula2ioasys.data.repositories

import com.example.desafioaula2ioasys.data.datasource.remote.BooksRemoteDatasource
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow

class BooksRepositoryImpl(
    private val booksRemoteDatasource: BooksRemoteDatasource
):BooksRepository {

    override fun getBooks(token: String, page: Int, amount: Int): Flow<ListBooksResponse> =
       booksRemoteDatasource.getBooks(token, page, amount)


}