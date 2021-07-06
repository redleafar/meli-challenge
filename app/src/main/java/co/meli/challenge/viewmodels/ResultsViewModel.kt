package co.meli.challenge.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.meli.data.datasources.impl.SearchDataSourceImpl
import co.meli.data.repository.impl.SearchRepositoryImpl
import co.meli.data.services.api.ApiService
import co.meli.domain.models.ResultWrapper
import co.meli.domain.models.SearchResponse
import co.meli.domain.usecases.impl.SearchUseCaseImpl
import kotlinx.coroutines.launch

class ResultsViewModel : ViewModel() {
    fun search(query: String) {
        val dataSource = SearchDataSourceImpl(ApiService())
        val repository = SearchRepositoryImpl(dataSource)
        val searchUseCase = SearchUseCaseImpl(repository)

        viewModelScope.launch {
            val result = searchUseCase<SearchResponse>(query)
            when {
                result is ResultWrapper.Success -> Log.d("meliresult", result.value.toString())
                result is ResultWrapper.GenericError -> Log.d("melierror", result.message.toString())
                result is ResultWrapper.NetworkError -> Log.d("melinetworkerror", "Revisa tu conexión")
            }
        }
    }
}