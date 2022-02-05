package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.domain.model.Book
import com.example.desafioaula2ioasys.domain.model.ListBooks
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import com.example.desafioaula2ioasys.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class BookListViewModel(
    val booksRepository: BooksRepository
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
            try {
                booksRepository.getBooks(booksPage, 20).collect { responseBooks ->
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
                        saveBooks(listBooksResponse!!.data ?: responseBooks.data)
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

//    private fun searchBooks(input: String): List<Book>? {
//        return if (input.trim().isEmpty()) {
//            listBooksResponse?.data
//        } else {
//            listBooksResponse?.data?.filter { book ->
//                book.title.contains(input, ignoreCase = true)
//            }
//        }
//    }

    fun search(input: String) {
        viewModelScope.launch {
//            searchBooks(input).let { books ->
//                if (books != null) {
//                    when {
//                        books.isNotEmpty() -> {
//
//                        }
//                        else -> {
//
//                        }
//                    }
//                }
//            }
        }
    }

    private fun saveBooks(bookList: List<Book>) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    booksRepository.saveBooks(bookList)
                }
                print("Sucess")
            } catch (err: Exception) {
                print(err)
            }
        }
    }
}