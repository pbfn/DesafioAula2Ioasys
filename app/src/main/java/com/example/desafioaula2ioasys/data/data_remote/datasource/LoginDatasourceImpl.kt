package com.example.desafioaula2ioasys.data.data_remote.datasource

import com.example.desafioaula2ioasys.data.datasource.LoginDatasource
import com.example.desafioaula2ioasys.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay

class LoginDatasourceImpl:LoginDatasource {
    override fun login(email:String,password:String): Flow<User> = flow {
        delay(3000)
        emit(User(
             birthdate = "15",
             gender = "M",
             id = "1",
             name="Pedro"
         ))
    }
}