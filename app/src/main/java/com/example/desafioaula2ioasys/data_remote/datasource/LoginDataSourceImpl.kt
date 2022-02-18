package com.example.desafioaula2ioasys.data_remote.datasource

import com.example.desafioaula2ioasys.data_remote.service.AuthService
import com.example.desafioaula2ioasys.data.datasource.remote.LoginRemoteDataSource
import com.example.desafioaula2ioasys.data_remote.mappers.toDomain
import com.example.desafioaula2ioasys.data_remote.model.LoginRequest
import com.example.desafioaula2ioasys.domain.exceptions.InvalidEmailException
import com.example.desafioaula2ioasys.domain.exceptions.InvalidLoginException
import com.example.desafioaula2ioasys.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception

class LoginDataSourceImpl(
    private val authService: AuthService
) : LoginRemoteDataSource {
    override fun login(email: String, password: String): Flow<User> = flow {
        val response = authService.doLogin(LoginRequest(email, password))
        val accessToken = response.headers()["authorization"]
        if (response.isSuccessful) {
            response.body()?.let { loginResponse ->
                emit(loginResponse.toDomain(accessToken ?: ""))
            }
        }else{
            emit( throw InvalidLoginException(response.message()))
        }

    }
}