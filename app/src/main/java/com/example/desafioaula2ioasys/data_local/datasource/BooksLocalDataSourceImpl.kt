package com.example.desafioaula2ioasys.data_local.datasource

import com.example.desafioaula2ioasys.data.datasource.local.BooksLocalDataSource
import com.example.desafioaula2ioasys.data_local.database.BookDao
import com.example.desafioaula2ioasys.data_local.mappers.toDao
import com.example.desafioaula2ioasys.data_local.mappers.toDomain
import com.example.desafioaula2ioasys.data_local.utils.LocalConstants.ACCESS_TOKEN_KEY
import com.example.desafioaula2ioasys.data_local.utils.SharedPreferencesHelper
import com.example.desafioaula2ioasys.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BooksLocalDataSourceImpl(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val bookDao: BookDao
) : BooksLocalDataSource {

    override fun getAccessToken(): Flow<String> = flow {
        emit(sharedPreferencesHelper.getString(ACCESS_TOKEN_KEY))
    }

    override fun saveBooks(bookList: List<Book>) = bookDao.addBooks(
        bookList = bookList.map { it.toDao() }
    )

    override fun getBooks(): Flow<List<Book>> = flow {
        emit(bookDao.getBooks().map { it.toDomain() })
    }


}