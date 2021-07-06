package co.meli.domain.usecases.impl

import co.meli.domain.models.ResultWrapper
import co.meli.domain.repositories.SearchRepository
import co.meli.domain.usecases.SearchUseCase

class SearchUseCaseImpl(private val searchRepository: SearchRepository) : SearchUseCase {
    override suspend fun <T : Any> invoke(query: String): ResultWrapper<T> =
        searchRepository(query)
}