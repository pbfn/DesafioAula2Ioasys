package com.example.desafioaula2ioasys.data.repositories

import com.example.desafioaula2ioasys.data.datasource.LoginDatasource
import com.example.desafioaula2ioasys.domain.model.User
import com.example.desafioaula2ioasys.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl(
    private val loginDatasource: LoginDatasource
) : LoginRepository {

    override fun doLogin(email:String,password:String): Flow<User> =
        loginDatasource.login(email,password)
}