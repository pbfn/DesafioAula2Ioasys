package com.example.desafioaula2ioasys.data.repositories

import com.example.desafioaula2ioasys.data.datasource.BooksDatasource
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow

class BooksRepositoryImpl(
    private val booksDatasource: BooksDatasource
):BooksRepository {

    override fun getBooks(token: String, page: Int, amount: Int): Flow<ListBooksResponse> =
       booksDatasource.getBooks(token, page, amount)


}