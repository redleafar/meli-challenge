package co.meli.challenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.meli.challenge.utils.ProductPagingSource
import co.meli.domain.models.Product
import co.meli.domain.usecases.SearchUseCase
import kotlinx.coroutines.flow.Flow

class ResultsViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    fun search(query: String): Flow<PagingData<Product>> {
        return Pager(PagingConfig(pageSize = 10)) {
            ProductPagingSource(searchUseCase, query)
        }.flow.cachedIn(viewModelScope)
    }
}