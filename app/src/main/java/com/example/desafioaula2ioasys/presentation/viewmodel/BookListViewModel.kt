package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.domain.model.BookResponse
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import com.example.desafioaula2ioasys.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class BookListViewModel(
    val booksRepository: BooksRepository
) : ViewModel() {

    val _listBooks: MutableLiveData<Resource<ListBooksResponse>> = MutableLiveData()
    val listBooks = _listBooks as LiveData<Resource<ListBooksResponse>>
    var booksPage: Int = 1
    var totalPages: Int = 100
    var listBooksResponse: ListBooksResponse? = null


    fun getListBooks() = viewModelScope.launch {
        if (booksPage < totalPages) {
            getBooks()
        }
    }

    private fun getBooks() {
        viewModelScope.launch {
            _listBooks.postValue(Resource.Loading())
            try {
                booksRepository.getBooks( booksPage, 20).collect { responseBooks ->
                    if (responseBooks.data.isNotEmpty()) {
                        booksPage++
                        totalPages = responseBooks.totalPages
                        if (listBooksResponse == null) {
                            listBooksResponse = responseBooks
                        } else {
                            val oldBooks = listBooksResponse?.data
                            val newBooks = responseBooks.data
                            oldBooks?.addAll(newBooks)
                        }
                        _listBooks.postValue(Resource.Success(listBooksResponse ?: responseBooks))
                    }
                }
            } catch (err: Exception) {

            }
        }
//        if (response.isSuccessful) {
//            response.body()?.let { resultResponse ->
//                booksPage++
//                totalPages = resultResponse.totalPages
//                if (listBooksResponse == null) {
//                    listBooksResponse = resultResponse
//                } else {
//                    val oldBooks = listBooksResponse?.data
//                    val newBooks = resultResponse.data
//                    oldBooks?.addAll(newBooks)
//                }
//                return Resource.Success(listBooksResponse ?: resultResponse)
//            }
//        }
//        return Resource.Error(response.message())

    }

    private fun searchBooks(input: String): List<BookResponse>? {
        return if (input.trim().isEmpty()) {
            listBooksResponse?.data
        } else {
            listBooksResponse?.data?.filter { book ->
                book.title.contains(input, ignoreCase = true)
            }
        }
    }

    fun search(input: String) {
        viewModelScope.launch {
            searchBooks(input).let { books ->
                if (books != null) {
                    when {
                        books.isNotEmpty() -> {

                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }
}