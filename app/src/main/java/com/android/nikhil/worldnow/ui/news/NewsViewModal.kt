package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.network.NewsInterface
import com.android.nikhil.worldnow.utils.MainResponse
import com.android.nikhil.worldnow.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class NewsViewModal @Inject constructor(): ViewModel() {

    private var newsLiveData = MutableLiveData<ArrayList<Result>>()
    @Inject lateinit var newsApi: NewsInterface

    fun getNews() {
        newsApi.getNews(BuildConfig.ApiKey)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map { mainRes -> mainRes.response.results }
            .subscribe({ value -> newsLiveData.postValue(value) }, { e -> e.printStackTrace() })
    }

  fun getNewsData() = newsLiveData
}