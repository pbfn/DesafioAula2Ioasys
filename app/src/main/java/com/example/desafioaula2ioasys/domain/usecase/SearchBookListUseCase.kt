package com.example.desafioaula2ioasys.domain.usecase

import com.example.desafioaula2ioasys.domain.model.ListBooks
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import com.example.desafioaula2ioasys.domain.usecase.utils.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SearchBookListUseCase(
    private val booksRepository: BooksRepository,
    scope: CoroutineScope
) : UseCase<SearchBookListUseCase.Params, ListBooks>(scope = scope) {


    data class Params(
        val titleSearch: String
    )

    override fun run(params: Params?): Flow<ListBooks> {
        return booksRepository.searchBooks(
            titleSearch = params?.titleSearch
        )
    }

}