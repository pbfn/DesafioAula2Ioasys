package com.example.desafioaula2ioasys.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafioaula2ioasys.repository.BookRepository
import com.example.desafioaula2ioasys.repository.LoginRepository

class ViewModelProviderBook(
    val repository: BookRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(repository) as T
    }

}