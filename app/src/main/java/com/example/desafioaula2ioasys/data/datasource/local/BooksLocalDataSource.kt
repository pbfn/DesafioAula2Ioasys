package com.example.desafioaula2ioasys.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface BooksLocalDataSource {
    fun getAccessToken():Flow<String>
}