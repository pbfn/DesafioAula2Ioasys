package com.example.desafioaula2ioasys.data.repositories

import com.example.desafioaula2ioasys.data.datasource.local.LoginLocalDataSource
import com.example.desafioaula2ioasys.data.datasource.remote.LoginRemoteDatasource
import com.example.desafioaula2ioasys.domain.model.User
import com.example.desafioaula2ioasys.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(
    private val loginRemoteDatasource: LoginRemoteDatasource,
    private val LoginLocalDatasource: LoginLocalDataSource
) : LoginRepository {

    override fun doLogin(email: String, password: String): Flow<User> = flow {
        loginRemoteDatasource.login(email, password).collect { userData ->
            LoginLocalDatasource.saveAccessToken(acessToken = userData.accessToken)
            emit(userData)
        }
    }

}