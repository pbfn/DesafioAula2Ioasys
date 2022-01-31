package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafioaula2ioasys.domain.repositories.BookRepository

class ViewModelProviderBook(
    val repository: BookRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookListViewModel(repository) as T
    }

}