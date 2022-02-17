package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.domain.model.ListBooks
import com.example.desafioaula2ioasys.domain.usecase.GetBookListUseCase
import com.example.desafioaula2ioasys.domain.usecase.SearchBookListUseCase
import com.example.desafioaula2ioasys.domain.usecase.utils.SaveBooksUseCase
import com.example.desafioaula2ioasys.util.Resource

import kotlinx.coroutines.launch

class BookListViewModel(
    val getBookListUseCase: GetBookListUseCase,
    val saveBooksUseCase: SaveBooksUseCase,
    val searchBookListUseCase: SearchBookListUseCase
) : ViewModel() {

    private val _listBooks: MutableLiveData<Resource<ListBooks>> = MutableLiveData()
    val listBooks = _listBooks as LiveData<Resource<ListBooks>>
    var booksPage: Double = 1.0
    var totalPages: Double = 100.0
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
                    page = booksPage
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

    fun searchBooks(titleSearch: String) {
        viewModelScope.launch {
            _listBooks.postValue(Resource.Loading())
            searchBookListUseCase(
                params = SearchBookListUseCase.Params(
                    titleSearch = titleSearch
                ),
                onSuccess = { list ->
                    if (list.data.isEmpty()) {
                        _listBooks.postValue(
                            Resource.Error(
                                "Não encontramos nenhum\n" +
                                        "livro com esses termos. "
                            ,list)
                        )
                    } else {
                        _listBooks.postValue(Resource.Success(list))
                    }
                },
                onError = {
                    _listBooks.postValue(it.message?.let { it1 -> Resource.Error(it1) })
                }
            )
        }
    }
}