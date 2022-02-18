package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.domain.exceptions.InvalidEmailException
import com.example.desafioaula2ioasys.domain.exceptions.InvalidLoginException
import com.example.desafioaula2ioasys.domain.exceptions.InvalidPassowordException
import com.example.desafioaula2ioasys.domain.repositories.LoginRepository
import com.example.desafioaula2ioasys.domain.usecase.LoginUseCase
import com.example.desafioaula2ioasys.util.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception


class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private var _responseLogin = MutableLiveData<Resource<String>>()
    var responseLogin: LiveData<Resource<String>> = _responseLogin


    fun doLogin(email: String, password: String) {
        loginUseCase(
            params = LoginUseCase.Params(
                email = email,
                password = password
            ),
            onSuccess = { user ->
                _responseLogin.postValue(Resource.Success(user.accessToken))
            },
            onError = { error ->
                var message = ""
                when (error) {
                    is InvalidEmailException -> {
                        message = "Email Invalido"
                    }
                    is InvalidPassowordException -> {
                        message = "Senha Invalida"
                    }
                    is InvalidLoginException -> {
                        message = "Login ou Senha invalidos"
                    }
                }

                _responseLogin.postValue(Resource.Error(message))

            }
        )
    }
}