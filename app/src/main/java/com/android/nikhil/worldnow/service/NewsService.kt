package com.android.nikhil.worldnow.service

import com.android.nikhil.worldnow.model.MainResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("search")
    fun getNews(@Query("api-key") apiKey: String): Observable<MainResponse>

}