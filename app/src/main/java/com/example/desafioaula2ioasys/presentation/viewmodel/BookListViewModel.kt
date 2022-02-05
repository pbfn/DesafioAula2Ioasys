package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.domain.model.ListBooks
import com.example.desafioaula2ioasys.domain.usecase.GetBookListUseCase
import com.example.desafioaula2ioasys.domain.usecase.utils.SaveBooksUseCase
import com.example.desafioaula2ioasys.util.Resource

import kotlinx.coroutines.launch

class BookListViewModel(
    val getBookListUseCase: GetBookListUseCase,
    val saveBooksUseCase: SaveBooksUseCase
) : ViewModel() {

    val _listBooks: MutableLiveData<Resource<ListBooks>> = MutableLiveData()
    val listBooks = _listBooks as LiveData<Resource<ListBooks>>
    var booksPage: Int = 1
    var totalPages: Int = 100
    var listBooksResponse: ListBooks? = null


    fun getListBooks() = viewModelScope.launch {
        if (booksPage < totalPages) {
            getBooks()
        }
    }

    private fun getBooks() {
        viewModelScope.launch {
            _listBooks.postValue(Resource.Loading())
            getBookListUseCase(
                params = GetBookListUseCase.Params(
                    page = booksPage,
                    titleSearch = ""
                ),
                onSuccess = { responseBooks ->
                    booksPage++
                    totalPages = responseBooks.totalPages
                    if (listBooksResponse == null) {
                        listBooksResponse = responseBooks
                    } else {
                        val oldBooks = listBooksResponse?.data
                        val newBooks = responseBooks.data
                        oldBooks?.addAll(newBooks)
                    }
                    saveBooksUseCase(
                        params = SaveBooksUseCase.Params(
                            bookList = listBooksResponse!!.data
                        )
                    )
                    _listBooks.postValue(Resource.Success(listBooksResponse ?: responseBooks))
                },
                onError = {
                    _listBooks.postValue(it.message?.let { it1 -> Resource.Error(it1) })
                }
            )
        }
    }

}