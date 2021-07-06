package co.meli.challenge.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import co.meli.challenge.R
import co.meli.challenge.viewmodels.ResultsViewModel

class ResultsActivity : AppCompatActivity() {
    val viewModel: ResultsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        viewModel.search("Motorola G6")
    }
}