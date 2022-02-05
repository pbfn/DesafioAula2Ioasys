package com.example.desafioaula2ioasys.domain.usecase.utils

import com.example.desafioaula2ioasys.domain.model.Book
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SaveBooksUseCase(
    private val booksRepository: BooksRepository,
    scope: CoroutineScope
) : UseCase<SaveBooksUseCase.Params, Unit>(scope = scope) {

    data class Params(
        val bookList: List<Book>
    )

    override fun run(params: Params?): Flow<Unit> = flowOf(
        booksRepository.saveBooks(bookList = params?.bookList ?: listOf())
    )

}