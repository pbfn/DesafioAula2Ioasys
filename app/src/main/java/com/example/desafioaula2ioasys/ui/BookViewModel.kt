package com.example.desafioaula2ioasys.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.models.ListBooksResponse
import com.example.desafioaula2ioasys.repository.BookRepository
import com.example.desafioaula2ioasys.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class BookViewModel(
    val repository: BookRepository
) : ViewModel() {

    val _listBooks: MutableLiveData<Resource<ListBooksResponse>> = MutableLiveData()


    var lastPage: Boolean = false

//    init {
//        getListBooks("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MWM5YzI5MGNjNDk4YjVjMDg4NDVlMGEiLCJ2bGQiOjE2NDIxNzA5MDg5NjgsImlhdCI6MTY0MjE3NDUwODk2OH0.1TH4Rqmn41BfawP0nqPbr43CE_zqy6uU1o0z0sKnQbw", 1, 20)
//    }

    fun getListBooks(token: String, page: Int, amount: Int) = viewModelScope.launch {
        _listBooks.postValue(Resource.Loading())
        if (!lastPage) {
            val response = repository.getBooks(token, page, amount)
            _listBooks.postValue(handleListBookResponse(response))
        }
    }

    private fun handleListBookResponse(response: Response<ListBooksResponse>): Resource<ListBooksResponse>? {
        if (response.isSuccessful) {
                response.body()?.let { resultResponse->
                    return Resource.Success(resultResponse)
                }
            return null
        } else {
            return null
        }
    }

}