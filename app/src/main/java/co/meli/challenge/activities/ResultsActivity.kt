package co.meli.challenge.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import co.meli.challenge.R
import co.meli.challenge.activities.ProductDetailActivity.Companion.PRODUCT_OBJECT
import co.meli.challenge.adapters.ProductsAdapter
import co.meli.challenge.adapters.ProductsLoadStateAdapter
import co.meli.challenge.databinding.ActivityResultsBinding
import co.meli.challenge.viewmodels.ResultsViewModel
import co.meli.challenge.viewmodels.factory.ResultsViewModelFactory
import co.meli.data.datasources.impl.SearchDataSourceImpl
import co.meli.data.repository.impl.SearchRepositoryImpl
import co.meli.data.services.api.ApiService
import co.meli.domain.models.Product
import co.meli.domain.usecases.impl.SearchUseCaseImpl
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding
    private lateinit var viewModel: ResultsViewModel

    private val onProductClick: (product: Product) -> Unit = {
        goToProductDetail(it)
    }

    companion object {
        const val MELI_QUERY = "MELI_QUERY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        initAdapter()
    }

    private fun initBinding() {
        binding = ActivityResultsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initViewModel() {
        val dataSource = SearchDataSourceImpl(ApiService())
        val repository = SearchRepositoryImpl(dataSource)
        val searchUseCase = SearchUseCaseImpl(repository)

        val factory = ResultsViewModelFactory(searchUseCase)
        viewModel = ViewModelProvider(this, factory).get(ResultsViewModel::class.java)
    }

    private fun initAdapter() {
        val productsAdapter = ProductsAdapter(this, onProductClick)
        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(this)
            it.setHasFixedSize(true)
            it.adapter = productsAdapter.withLoadStateHeaderAndFooter(
                header = ProductsLoadStateAdapter { productsAdapter.retry() },
                footer = ProductsLoadStateAdapter { productsAdapter.retry() }
            )

            productsAdapter.addLoadStateListener { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && productsAdapter.itemCount == 0
                showEmptyList(isListEmpty)

                binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.refresh as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        this,
                        getString(R.string.check_your_connection),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        val query = intent.extras?.get(MELI_QUERY)

        lifecycleScope.launch {
            viewModel.search(query as String).collectLatest { pagedData ->
                productsAdapter.submitData(pagedData)
            }
        }

        binding.retryButton.setOnClickListener { productsAdapter.retry() }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    private fun goToProductDetail(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java).apply {
            putExtra(PRODUCT_OBJECT, product)
        }

        startActivity(intent)
    }
}