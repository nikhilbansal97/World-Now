package com.android.nikhil.worldnow.network

import com.android.nikhil.worldnow.utils.MainResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface NewsInterface {

    @GET("search")
    fun getNews(@Query("api-key") apiKey: String): Call<MainResponse>

}