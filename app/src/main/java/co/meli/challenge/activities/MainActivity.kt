package co.meli.challenge.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.meli.challenge.activities.ResultsActivity.Companion.MELI_QUERY
import co.meli.challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val EMPTY_STRING = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSearch.setOnClickListener {
            goToSearchResults(binding.textInputEditTextSearch.text.toString())
        }
    }

    private fun goToSearchResults(query: String) {
        if (query != EMPTY_STRING) {
            intent = Intent(this, ResultsActivity::class.java).apply {
                putExtra(MELI_QUERY, query)
            }

            startActivity(intent)
        } else {
            binding.textInputEditTextSearch.error = "Ingresa un término de búsqueda"
        }
    }
}