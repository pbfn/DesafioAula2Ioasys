package com.example.desafioaula2ioasys.data.datasource.remote

import com.example.desafioaula2ioasys.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRemoteDataSource {

    fun login(email:String,password:String) : Flow<User>

}