package com.android.nikhil.worldnow.data.source.remote

import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.data.source.datasource.NewsDataSource
import com.android.nikhil.worldnow.network.NewsInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRemoteDataSource (var newsApi : NewsInterface) : NewsDataSource {



    override fun getNews(callback: NewsDataSource.getNewsCallback) {
        newsApi.getNews(BuildConfig.ApiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { it.response.results }
                .subscribe({ value -> if (value!=null) callback.onNewsLoaded(value) else callback.onDatanotAvalaible()}){
                    error -> callback.onError(error.message)
                }
    }

}