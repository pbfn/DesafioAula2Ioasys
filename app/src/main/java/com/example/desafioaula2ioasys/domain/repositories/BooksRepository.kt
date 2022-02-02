package com.example.desafioaula2ioasys.domain.repositories

import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    fun getBooks(page:Int,amount:Int):Flow<ListBooksResponse>

}