package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse
import com.example.desafioaula2ioasys.repository.BookRepository
import com.example.desafioaula2ioasys.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class BookViewModel(
    val repository: BookRepository
) : ViewModel() {

    val listBooks: MutableLiveData<Resource<ListBooksResponse>> = MutableLiveData()
    var booksPage: Int = 1
    var totalPages: Int = 100
    var listBooksResponse: ListBooksResponse? = null


    fun getListBooks(token: String) = viewModelScope.launch {
        if(booksPage < totalPages){
            listBooks.postValue(Resource.Loading())
            val response = repository.getBooks(token, booksPage, 20)
            listBooks.postValue(handleListBookResponse(response))
        }
    }

    private fun handleListBookResponse(response: Response<ListBooksResponse>): Resource<ListBooksResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                booksPage++
                totalPages = resultResponse.totalPages
                if (listBooksResponse == null) {
                    listBooksResponse = resultResponse
                } else {
                    val oldBooks = listBooksResponse?.data
                    val newBooks = resultResponse.data
                    oldBooks?.addAll(newBooks)
                }
                return Resource.Success(listBooksResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())

    }

}