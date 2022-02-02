package com.example.desafioaula2ioasys.data_local.datasource

import com.example.desafioaula2ioasys.data.datasource.local.LoginLocalDataSource
import com.example.desafioaula2ioasys.data_local.utils.LocalConstants.ACCESS_TOKEN_KEY
import com.example.desafioaula2ioasys.data_local.utils.SharedPreferencesHelper

class LoginLocalDataSourceImpl(
  private val sharedPreferencesHelper: SharedPreferencesHelper
) :LoginLocalDataSource {
    override fun saveAccessToken(acessToken: String) {
        sharedPreferencesHelper.saveString(
            key = ACCESS_TOKEN_KEY,
            value = acessToken
        )
    }
}