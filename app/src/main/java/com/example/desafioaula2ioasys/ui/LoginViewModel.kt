package com.example.desafioaula2ioasys.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.models.User
import com.example.desafioaula2ioasys.repository.LoginRepository
import com.example.desafioaula2ioasys.util.Resource
import kotlinx.coroutines.launch

import org.json.JSONObject
import java.lang.Exception


class LoginViewModel(
    val repository: LoginRepository
):ViewModel() {

    private var _responseLogin = MutableLiveData<Resource<String>>()
    var responseLogin: LiveData<Resource<String>> = _responseLogin

    init {
        doLogin("desafio@ioasys.com.br","12341234")
    }

    fun doLogin(email:String,password:String) = viewModelScope.launch {
        val user = User(email,password)
        _responseLogin.postValue(Resource.Loading())
        val response = repository.doLogin(user)
        if(response.isSuccessful){
           val token =  response.headers().toMultimap()["authorization"]?.last().toString()

            _responseLogin.postValue(Resource.Success(token))
        }else{
            val errorMessage = try {
                val jObjError = JSONObject(response.errorBody()!!.string())
                jObjError.getJSONObject("errors").getString("message").toString()
            } catch (e: Exception) {
                e.message.toString()
            }
         _responseLogin.postValue(Resource.Error(errorMessage))
        }
    }

}