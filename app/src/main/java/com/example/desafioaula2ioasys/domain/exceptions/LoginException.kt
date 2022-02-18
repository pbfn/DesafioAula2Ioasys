package com.example.desafioaula2ioasys.domain.exceptions

open class LoginException(message:String) : Exception()

class InvalidEmailException(message: String) : LoginException(message)
class InvalidPassowordException(message: String) : LoginException(message)
class InvalidLoginException (message: String):LoginException(message)