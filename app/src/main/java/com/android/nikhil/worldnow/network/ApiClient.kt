package com.android.nikhil.worldnow.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("PrivatePropertyName")
class ApiClient {

    companion object {
        private const val BASE_URL = "https://content.guardianapis.com/"
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            if (retrofit == null)
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            return retrofit
        }
    }

}