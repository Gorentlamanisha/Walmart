package com.example.walmartcountriesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryApiService {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): List<Country>

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/"

        fun create(): CountryApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(this.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(CountryApiService::class.java)
        }
    }
}