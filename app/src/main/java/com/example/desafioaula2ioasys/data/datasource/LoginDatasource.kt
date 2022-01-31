package com.example.desafioaula2ioasys.data.datasource

import com.example.desafioaula2ioasys.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginDatasource {

    fun login(email:String,password:String) : Flow<User>

}