package com.example.desafioaula2ioasys.data.data_remote.datasource

import com.example.desafioaula2ioasys.data.datasource.BooksDatasource
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class BooksDatasourceImpl: BooksDatasource {
    override fun getBooks(token: String, page: Int, amount: Int): Flow<ListBooksResponse> = flow {
        //emit()
    }
}