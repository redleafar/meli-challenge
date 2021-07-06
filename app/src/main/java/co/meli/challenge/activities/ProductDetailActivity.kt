package co.meli.challenge.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.meli.challenge.R
import co.meli.challenge.databinding.ActivityProductDetailBinding
import co.meli.domain.models.Product

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product

    companion object {
        const val PRODUCT_OBJECT = "PRODUCT_OBJECT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        product = intent.extras?.getSerializable(PRODUCT_OBJECT) as Product
        setValues(product)
    }

    private fun setValues(product: Product) {
        binding.textProductTitle.text = product.title
        binding.textProductPrice.text = this.getString(R.string.price, product.price)
        if (product.shipping?.freeShipping == true) binding.textProductShipping.visibility = View.VISIBLE
        binding.textProductInstallments.text = this.getString(
            R.string.installments,
            product.installments?.quantity,
            product.installments?.amount
        )
    }
}