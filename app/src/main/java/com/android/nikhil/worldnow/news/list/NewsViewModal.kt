package com.android.nikhil.worldnow.news.list

import android.arch.lifecycle.MutableLiveData
import com.android.nikhil.worldnow.base.BaseViewModel
import com.android.nikhil.worldnow.model.Result
import com.android.nikhil.worldnow.repository.NewsRepository
import javax.inject.Inject

class NewsViewModal @Inject constructor() : BaseViewModel() {

  private var newsLiveData = MutableLiveData<List<Result>>()
  private var error = MutableLiveData<Boolean>()
  @Inject lateinit var newsRepository: NewsRepository

  fun getNews() {
    newsLiveData.postValue(newsRepository.getNews())
  }

  fun getNewsData() = newsLiveData

  fun getErrorLiveData() = error
}