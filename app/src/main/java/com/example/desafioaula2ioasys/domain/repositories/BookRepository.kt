package com.example.desafioaula2ioasys.domain.repositories

import com.example.desafioaula2ioasys.api.RetrofitInstance

class BookRepository {

    suspend fun getBooks(token:String,page:Int,amount:Int) =
        RetrofitInstance.api.getBooks(token,page,amount)

}