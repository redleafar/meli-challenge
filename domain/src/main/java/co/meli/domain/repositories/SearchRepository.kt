package co.meli.domain.repositories

import co.meli.domain.models.ResultWrapper

interface SearchRepository {
    suspend operator fun <T : Any> invoke(query: String, page: Int): ResultWrapper<T>
}