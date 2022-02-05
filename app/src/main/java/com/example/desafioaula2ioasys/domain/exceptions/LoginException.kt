package com.example.desafioaula2ioasys.domain.exceptions

open class LoginException : Exception()

class InvalidEmailException : LoginException()
class InvalidPassowordException : LoginException()