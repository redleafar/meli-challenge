package co.meli.challenge.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.meli.domain.models.Product
import co.meli.domain.models.ResultWrapper
import co.meli.domain.models.SearchResponse
import co.meli.domain.usecases.SearchUseCase
import com.bumptech.glide.load.HttpException
import java.io.IOException

class ProductPagingSource(
    private val searchUseCase: SearchUseCase,
    private val query: String
) : PagingSource<Int, Product>() {

    companion object {
        private const val MELI_STARTING_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val nextPageNumber = params.key ?: MELI_STARTING_PAGE_INDEX
        val result = searchUseCase<SearchResponse>(query, nextPageNumber)

        return when (result) {
            is ResultWrapper.Success -> LoadResult.Page(
                data = result.value.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < result.value.paging?.total ?: 0) nextPageNumber + 1 else null
            )
            is ResultWrapper.GenericError -> LoadResult.Error(HttpException(result.message))
            else -> LoadResult.Error(IOException("Error de conexión"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}