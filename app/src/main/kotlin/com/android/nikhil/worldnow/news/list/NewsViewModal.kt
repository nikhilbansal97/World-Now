package com.android.nikhil.worldnow.news.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.android.nikhil.worldnow.base.BaseViewModel
import com.android.nikhil.worldnow.model.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModal @Inject constructor(private val newsRepository: NewsRepository) :
    BaseViewModel() {

  private var error = MutableLiveData<Boolean>()
  private var newsLiveData = MutableLiveData<List<Result>>()

  fun getNews() {
    viewModelScope.launch {
      val news = newsRepository.getNews()
      newsLiveData.postValue(news)
    }
  }

  fun getNewsData() = newsLiveData

  fun getErrorLiveData() = error

  fun observeRepository() {
    newsLiveData = Transformations.switchMap(newsRepository.newsListLiveData) { resultsList ->
      val data = MutableLiveData<List<Result>>()
      data.value = resultsList
      data
    } as MutableLiveData<List<Result>>
  }
}