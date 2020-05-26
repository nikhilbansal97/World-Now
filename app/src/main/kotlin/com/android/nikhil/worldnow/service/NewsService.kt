package com.android.nikhil.worldnow.service

import com.android.nikhil.worldnow.model.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

  @GET("search")
  fun getNews(@Query("api-key") apiKey: String): Deferred<NewsResponse>

}