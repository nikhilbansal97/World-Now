package com.android.nikhil.worldnow.news.list.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.model.Result
import com.android.nikhil.worldnow.service.NewsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModal @Inject constructor(): ViewModel() {

    private var newsLiveData = MutableLiveData<List<Result>>()
    @Inject lateinit var newsApi: NewsService

    fun getNews() {
        newsApi.getNews(BuildConfig.ApiKey)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map { mainRes -> mainRes.response?.results }
            .subscribe({ value -> newsLiveData.postValue(value) }, { e -> e.printStackTrace() })
    }

  fun getNewsData() = newsLiveData
}