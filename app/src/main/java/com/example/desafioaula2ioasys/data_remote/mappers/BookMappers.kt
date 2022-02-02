package com.example.desafioaula2ioasys.data_remote.mappers


import com.example.desafioaula2ioasys.data_remote.model.BooksListResponse
import com.example.desafioaula2ioasys.domain.model.ListBooksResponse

fun BooksListResponse.toDomain() = ListBooksResponse(
    data = this.data,
    page = this.page,
    totalItems = totalItems,
    totalPages = this.totalPages
)