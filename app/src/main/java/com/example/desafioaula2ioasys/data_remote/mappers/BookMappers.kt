package com.example.desafioaula2ioasys.data_remote.mappers


import com.example.desafioaula2ioasys.data_remote.model.BooksListResponse
import com.example.desafioaula2ioasys.domain.model.ListBooks

fun BooksListResponse.toDomain() = ListBooks(
    data = data.toDomain(),
    page = this.page,
    totalItems = totalItems,
    totalPages = this.totalPages
)