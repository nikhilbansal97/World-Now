package com.android.nikhil.worldnow.news.list

import android.arch.lifecycle.MutableLiveData
import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.base.BaseViewModel
import com.android.nikhil.worldnow.model.Result
import com.android.nikhil.worldnow.service.NewsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModal @Inject constructor() : BaseViewModel() {

  private var newsLiveData = MutableLiveData<List<Result>>()
  private var error = MutableLiveData<Boolean>()
  @Inject lateinit var newsApi: NewsService

  fun getNews() {
    addDisposable(
        newsApi.getNews(BuildConfig.ApiKey)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map { mainRes -> mainRes.response?.results }
            .subscribe({ value -> newsLiveData.postValue(value) }, { e -> handleError(e) })
    )
  }

  private fun handleError(e: Throwable) {
    e.printStackTrace()
    error.postValue(true)
  }

  fun getNewsData() = newsLiveData

  fun getErrorLiveData() = error
}