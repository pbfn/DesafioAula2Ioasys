package com.example.desafioaula2ioasys.data.datasource.remote

import com.example.desafioaula2ioasys.domain.model.ListBooks
import kotlinx.coroutines.flow.Flow

interface BooksRemoteDataSource {

    fun getBooks(token: String, page: Int, amount: Int, titleSearch: String): Flow<ListBooks>

}