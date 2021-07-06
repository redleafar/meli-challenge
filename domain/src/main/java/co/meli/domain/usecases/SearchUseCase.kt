package co.meli.domain.usecases

import co.meli.domain.models.ResultWrapper

interface SearchUseCase {
    suspend operator fun <T : Any> invoke(query: String): ResultWrapper<T>
}