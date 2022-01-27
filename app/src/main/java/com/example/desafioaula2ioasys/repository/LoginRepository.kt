package com.example.desafioaula2ioasys.repository

import com.example.desafioaula2ioasys.api.RetrofitInstance
import com.example.desafioaula2ioasys.domain.model.User

class LoginRepository {

    suspend fun doLogin(user: User) =
        RetrofitInstance.api.doLogin(user)

}