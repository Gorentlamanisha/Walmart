package com.example.walmartcountriesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val apiService = CountryApiService.create()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = this._countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = this._error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = this._isLoading

    init {
        this.fetchCountries()
    }

    private fun fetchCountries() {
        this.viewModelScope.launch {
            try {
                this@CountryViewModel._isLoading.value = true
                val result = this@CountryViewModel.apiService.getCountries()
                this@CountryViewModel._countries.value = result
                this@CountryViewModel._isLoading.value = false
            } catch (e: Exception) {
                this@CountryViewModel._error.value = "Error fetching countries: ${e.message}"
                this@CountryViewModel._isLoading.value = false
            }
        }
    }
}