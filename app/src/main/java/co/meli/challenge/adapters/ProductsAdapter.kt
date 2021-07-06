package co.meli.challenge.adapters

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.meli.challenge.R
import co.meli.challenge.databinding.ItemProductBinding
import co.meli.domain.models.Product

class ProductsAdapter(
    private val context: Context,
    private val onProductClick: (product: Product) -> Unit
) : PagingDataAdapter<Product, ProductsAdapter.ProductsViewHolder>(PRODUCTS_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsViewHolder {
        return ProductsViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                getItem(layoutPosition)?.let { product -> onProductClick(product) }
            }
        }
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindProduct(it) }
    }

    inner class ProductsViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindProduct(item: Product) = with(binding) {
            textProductTitle.text = item.title
            textProductPrice.text = context.getString(R.string.price, item.price)
            if (item.shipping?.freeShipping == true) textProductShipping.visibility = View.VISIBLE
            textProductInstallments.text = context.getString(
                R.string.installments,
                item.installments?.quantity,
                item.installments?.amount
            )
        }
    }

    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem == newItem
        }
    }
}