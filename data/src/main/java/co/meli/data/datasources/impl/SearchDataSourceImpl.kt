package co.meli.data.datasources.impl

import co.meli.data.datasources.SearchDataSource
import co.meli.data.services.api.ApiService
import co.meli.data.services.api.SafeApiRequest
import co.meli.domain.models.ResultWrapper

class SearchDataSourceImpl(
    private val apiService: ApiService
) : SearchDataSource, SafeApiRequest() {
    override suspend operator fun <T : Any> invoke(query: String, page: Int): ResultWrapper<T> {
        return apiRequest {
            apiService.getSearchResponse(query, page) as T
        }
    }
}