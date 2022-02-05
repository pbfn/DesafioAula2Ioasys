package com.example.desafioaula2ioasys.domain.usecase

import com.example.desafioaula2ioasys.domain.exceptions.InvalidEmailException
import com.example.desafioaula2ioasys.domain.exceptions.InvalidPassowordException
import com.example.desafioaula2ioasys.domain.model.User
import com.example.desafioaula2ioasys.domain.repositories.LoginRepository
import com.example.desafioaula2ioasys.domain.usecase.utils.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val loginRepository: LoginRepository,
    scope: CoroutineScope
) : UseCase<LoginUseCase.Params, User>(scope = scope) {

    override fun run(params: Params?): Flow<User> = when {
        params?.email?.isEmpty() == true -> throw InvalidEmailException()
        params?.password?.isEmpty() == true -> throw InvalidPassowordException()
        else -> loginRepository.doLogin(
            email = params?.email ?:"",
            password = params?.password?:""
        )
    }


    data class Params(
        val email: String,
        val password: String
    )


}