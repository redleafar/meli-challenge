package co.meli.data.repository.impl

import co.meli.data.datasources.SearchDataSource
import co.meli.domain.models.ResultWrapper
import co.meli.domain.repositories.SearchRepository

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource) : SearchRepository {
    override suspend fun <T : Any> invoke(query: String, page: Int): ResultWrapper<T> =
        searchDataSource(query, page)
}