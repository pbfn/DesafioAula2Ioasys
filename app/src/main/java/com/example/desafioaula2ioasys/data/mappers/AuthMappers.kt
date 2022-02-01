package com.example.desafioaula2ioasys.data.mappers

import com.example.desafioaula2ioasys.data.model.LoginResponse
import com.example.desafioaula2ioasys.domain.model.User

fun LoginResponse.toDomain(accessToken:String) = User(
    name = this.name,
    birthdate = this.birthdate,
    gender = this.gender,
    id = this.id,
    accessToken = accessToken
)