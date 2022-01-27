package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafioaula2ioasys.repository.LoginRepository

class ViewModelProviderLogin(
    val repository: LoginRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }

}