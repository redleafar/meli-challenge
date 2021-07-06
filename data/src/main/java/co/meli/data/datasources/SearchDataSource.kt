package co.meli.data.datasources

import co.meli.domain.models.ResultWrapper

interface SearchDataSource {
    suspend operator fun <T : Any> invoke(query: String): ResultWrapper<T>
}