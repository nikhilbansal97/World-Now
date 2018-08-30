package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.network.NewsInterface
import com.android.nikhil.worldnow.utils.MainResponse
import com.android.nikhil.worldnow.utils.Result
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class NewsViewModal @Inject constructor(): ViewModel() {

    private var newsLiveData = MutableLiveData<ArrayList<Result>>()
    @Inject lateinit var newsApi: NewsInterface

    fun getNewsData(): MutableLiveData<ArrayList<Result>> = newsLiveData

    fun getNews() {
        val callNews = newsApi.getNews(BuildConfig.ApiKey)
        callNews.enqueue(object: Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>?, mainResponse: retrofit2.Response<MainResponse>?) {
                val newsResponse = mainResponse?.body()
                val body = newsResponse?.response
                if (body?.results != null) newsLiveData.postValue(body.results)
                else Log.d("", "results are null")
            }
            override fun onFailure(call: Call<MainResponse>?, t: Throwable?) {
                Log.d("", t?.localizedMessage)
            }
        })
    }
}