package com.example.desafioaula2ioasys.domain.usecase

import com.example.desafioaula2ioasys.domain.model.ListBooks
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import com.example.desafioaula2ioasys.domain.usecase.utils.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetBookListUseCase(
    private val booksRepository: BooksRepository,
    scope: CoroutineScope
) : UseCase<GetBookListUseCase.Params, ListBooks>(scope = scope) {


    override fun run(params: Params?): Flow<ListBooks> {
        return if (params != null) {
            booksRepository.getBooks(
                page = params.page,
                amount = 20
            )
        }else{
            booksRepository.getBooks(
                page = 10,
                amount = 20
            )
        }
    }

    data class Params(
        val page: Int,
    )


}