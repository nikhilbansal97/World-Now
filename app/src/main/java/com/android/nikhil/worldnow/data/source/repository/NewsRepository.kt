package com.android.nikhil.worldnow.data.source.repository

import com.android.nikhil.worldnow.data.model.main_news.Result
import com.android.nikhil.worldnow.data.source.datasource.NewsDataSource

open class NewsRepository(
        val remoteDataSource : NewsDataSource
) : NewsDataSource {

    override fun getNews(callback: NewsDataSource.getNewsCallback) {
        remoteDataSource.getNews(object : NewsDataSource.getNewsCallback{
            override fun onNewsLoaded(data: List<Result>?) {
                callback.onNewsLoaded(data)
            }

            override fun onDatanotAvalaible() {
                callback.onDatanotAvalaible()
            }

            override fun onError(errorMessage: String?) {
                callback.onError(errorMessage)
            }
        })
    }

}