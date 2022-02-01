package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository

class ViewModelProviderBook(
    val repository: BooksRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookListViewModel(repository) as T
    }

}