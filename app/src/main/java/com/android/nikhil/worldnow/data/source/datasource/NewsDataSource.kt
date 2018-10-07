package com.android.nikhil.worldnow.data.source.datasource

import com.android.nikhil.worldnow.data.model.main_news.Result

interface NewsDataSource {
    fun getNews(callback : getNewsCallback)

    interface getNewsCallback {
        fun onNewsLoaded(data : List<Result>?)
        fun onDatanotAvalaible()
        fun onError(errorMessage : String?)
    }
}