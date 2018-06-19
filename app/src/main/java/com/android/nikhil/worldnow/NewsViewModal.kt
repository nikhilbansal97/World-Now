package com.android.nikhil.worldnow

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class NewsViewModal(): ViewModel() {

    private var newsLiveData = MutableLiveData<ArrayList<Result>>()

    fun updateNewsDate(list: ArrayList<Result>) {
        newsLiveData.postValue(list)
    }

    fun getNewsData(): MutableLiveData<ArrayList<Result>> = newsLiveData
}