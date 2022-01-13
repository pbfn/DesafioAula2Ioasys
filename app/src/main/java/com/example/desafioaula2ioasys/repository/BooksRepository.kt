package com.example.desafioaula2ioasys.repository

import com.example.desafioaula2ioasys.api.RetrofitInstance
import com.example.desafioaula2ioasys.models.User

class BooksRepository {

    suspend fun doLogin(user: User) =
        RetrofitInstance.api.doLogin(user)

}