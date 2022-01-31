package com.example.desafioaula2ioasys.domain.repositories

import com.example.desafioaula2ioasys.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun doLogin(email:String,password:String): Flow<User>

}