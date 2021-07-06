package co.meli.challenge.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.meli.challenge.viewmodels.ResultsViewModel
import co.meli.domain.usecases.SearchUseCase

@Suppress("UNCHECKED_CAST")
class ResultsViewModelFactory(
    private val searchUseCase: SearchUseCase
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultsViewModel(searchUseCase) as T
    }
}