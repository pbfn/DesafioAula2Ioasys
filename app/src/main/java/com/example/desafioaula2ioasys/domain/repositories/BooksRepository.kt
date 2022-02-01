package com.example.desafioaula2ioasys.domain.repositories

import com.example.desafioaula2ioasys.api.RetrofitInstance
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    fun getBooks(token:String,page:Int,amount:Int):Flow<ListBooksResponse>

}