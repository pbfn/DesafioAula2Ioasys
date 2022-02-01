package com.example.desafioaula2ioasys.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioaula2ioasys.domain.repositories.LoginRepository
import com.example.desafioaula2ioasys.util.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception


class LoginViewModel(
    private val loginRepository: LoginRepository
):ViewModel() {

    private var _responseLogin = MutableLiveData<Resource<String>>()
    var responseLogin: LiveData<Resource<String>> = _responseLogin

    init {
        doLogin("desafio@ioasys.com.br","12341234")
    }

    fun doLogin(email:String,password:String)   {
        viewModelScope.launch{
            _responseLogin.postValue(Resource.Loading())
            try {
                loginRepository.doLogin(email, password).collect { user->
                    if(user.accessToken.isNotEmpty()){
                       _responseLogin.postValue(Resource.Success(user.accessToken))
                    }
                }
            }catch (err:Exception){

            }
        }
    //        val user = User(email,password)

//        val response = loginRepository.doLogin(user)
//        if(response.isSuccessful){
//           val token =  response.headers().toMultimap()["authorization"]?.last().toString()
//            _responseLogin.postValue(Resource.Success(token))
//        }else{
//            val errorMessage = try {
//                val jObjError = JSONObject(response.errorBody()!!.string())
//                jObjError.getJSONObject("errors").getString("message").toString()
//            } catch (e: Exception) {
//                e.message.toString()
//            }
//         _responseLogin.postValue(Resource.Error(errorMessage))
//        }
    }

}