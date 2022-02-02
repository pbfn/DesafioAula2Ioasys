package com.example.desafioaula2ioasys.data_local.datasource

import com.example.desafioaula2ioasys.data.datasource.local.BooksLocalDataSource
import com.example.desafioaula2ioasys.data_local.utils.LocalConstants.ACCESS_TOKEN_KEY
import com.example.desafioaula2ioasys.data_local.utils.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BooksLocalDataSourceImpl(
    private  val sharedPreferencesHelper: SharedPreferencesHelper
):BooksLocalDataSource {

    override fun getAccessToken(): Flow<String> = flow {
        emit(sharedPreferencesHelper.getString(ACCESS_TOKEN_KEY))
    }


}