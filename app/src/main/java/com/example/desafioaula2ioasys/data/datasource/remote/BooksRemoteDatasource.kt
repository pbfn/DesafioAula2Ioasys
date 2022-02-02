package com.example.desafioaula2ioasys.data.datasource.remote

import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import kotlinx.coroutines.flow.Flow

interface BooksRemoteDatasource {

    fun getBooks(token: String, page: Int, amount: Int):Flow<ListBooksResponse>

}