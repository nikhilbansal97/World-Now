package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.data.model.main_news.Result
import com.android.nikhil.worldnow.data.source.datasource.NewsDataSource
import com.android.nikhil.worldnow.data.source.repository.NewsRepository
import com.android.nikhil.worldnow.network.NewsInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import javax.inject.Inject

class NewsViewModal @Inject constructor(): ViewModel() {

    private var newsLiveData = MutableLiveData<List<Result>>()
    @Inject lateinit var repository : NewsRepository

    fun getNews() {
        repository.remoteDataSource.getNews(object : NewsDataSource.getNewsCallback{
            override fun onNewsLoaded(data: List<Result>?) {
                newsLiveData.postValue(data)
            }

            override fun onDatanotAvalaible() {

            }

            override fun onError(errorMessage: String?) {

            }
        })
    }

  fun getNewsData() = newsLiveData
}