package com.example.desafioaula2ioasys.data.repositories

import com.example.desafioaula2ioasys.data.datasource.local.LoginLocalDataSource
import com.example.desafioaula2ioasys.data.datasource.remote.LoginRemoteDataSource
import com.example.desafioaula2ioasys.domain.model.User
import com.example.desafioaula2ioasys.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val LoginLocalDatasource: LoginLocalDataSource
) : LoginRepository {

    override fun doLogin(email: String, password: String): Flow<User> = flow {
        loginRemoteDataSource.login(email, password).collect { userData ->
            LoginLocalDatasource.saveAccessToken(acessToken = userData.accessToken)
            emit(userData)
        }
    }

}