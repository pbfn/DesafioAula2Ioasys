package com.example.desafioaula2ioasys.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafioaula2ioasys.repository.BooksRepository

class ViewModelProviderBook(
    val repository: BooksRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }

}