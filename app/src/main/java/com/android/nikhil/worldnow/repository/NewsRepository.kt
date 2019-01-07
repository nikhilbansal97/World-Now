package com.android.nikhil.worldnow.repository

import android.arch.lifecycle.MutableLiveData
import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.model.Result
import com.android.nikhil.worldnow.service.NewsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import javax.inject.Inject

/*
* NewsRepository class is responsible for providing the news to the view model.
* First the local data will be provided and then it will be updated by the server's response.
*/

class NewsRepository @Inject constructor() {

  @Inject lateinit var newsService: NewsService
  private var compositeDisposable = CompositeDisposable()
  private val realm = Realm.getDefaultInstance()
  private val newsListLiveData = MutableLiveData<ArrayList<Result>>()

  /*
  * Main method exposed to the viewmodel to get the news.
  */
  fun getNews(): ArrayList<Result> {
    val news = getNewsFromDb()
    getNewsFromServer()
    return news
  }

  /*
  * Get the news from Realm Database
  */
  private fun getNewsFromDb(): ArrayList<Result> {
    realm.beginTransaction()
    val realmResults = realm.where(Result::class.java)
        .findAll()
    val results = ArrayList<Result>()
    for (result in realmResults) {
      if (result != null) {
        results.add(result)
      }
    }
    realm.commitTransaction()
    return results
  }

  /*
  * Save the news in the Realm database
  */
  private fun saveNewsInDb(newsList: ArrayList<Result>) {
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    realm.insert(newsList)
    realm.commitTransaction()
  }

  /*
  * Get the news from the server
  */
  private fun getNewsFromServer() {
    addDisposable(
        newsService.getNews(BuildConfig.ApiKey)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map { mainRes -> mainRes.response?.results }
            .subscribe({ value -> processNews(value) }, { e -> e.printStackTrace() })
    )
  }

  /*
  * Update the database and notify the viewmodel about the new news items received.
  */
  private fun processNews(value: List<Result>?) {
    if (value != null) {
      saveNewsInDb(value as ArrayList<Result>)
      newsListLiveData.postValue(value)
    }
  }

  private fun addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
  }
}