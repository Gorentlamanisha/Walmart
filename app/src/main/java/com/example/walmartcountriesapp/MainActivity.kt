package com.example.walmartcountriesapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        recyclerView = findViewById(R.id.countriesRecyclerView)
        progressBar = findViewById(R.id.progressBar)
        errorTextView = findViewById(R.id.errorTextView)

        // Setup RecyclerView
        adapter = CountryAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]

        // Observe LiveData from ViewModel
        viewModel.countries.observe(this) { countries ->
            adapter.updateCountries(countries)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMessage ->
            if (errorMessage != null && errorMessage.isNotEmpty()) {
                errorTextView.text = errorMessage
                errorTextView.visibility = View.VISIBLE
            } else {
                errorTextView.visibility = View.GONE
            }
        }
    }
}