package com.android.nikhil.worldnow.repository

import android.arch.lifecycle.MutableLiveData
import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.model.Result
import com.android.nikhil.worldnow.service.NewsService
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
* NewsRepository class is responsible for providing the news to the view model.
* First the local data will be provided and then it will be updated by the server's response.
*/

class NewsRepository @Inject constructor() {

  private val realm = Realm.getDefaultInstance()

  val newsListLiveData = MutableLiveData<ArrayList<Result>>()
  @Inject lateinit var newsService: NewsService

  /*
  * Main method exposed to the viewmodel to get the news.
  */
  suspend fun getNews() = coroutineScope {
    async { getNewsFromServer() }
    launch(Dispatchers.Main) {
      newsListLiveData.postValue(getNewsFromDb())
    }
  }

  /*
  * Get the news from Realm Database
  */
  private suspend fun getNewsFromDb(): ArrayList<Result> {
    val results = ArrayList<Result>()
    coroutineScope {
      realm.beginTransaction()
      val realmResults = realm.where(Result::class.java)
          .findAll()
      for (result in realmResults) {
        if (result != null) {
          results.add(result)
        }
      }
      realm.commitTransaction()
    }
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
  private suspend fun getNewsFromServer() {
    try {
      val webResponse = newsService.getNews(BuildConfig.ApiKey).await()
      webResponse.response?.let {
        coroutineScope { processNews(it.results) }
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  /*
  * Update the database and notify the viewmodel about the new news items received.
  */
  private suspend fun processNews(value: List<Result>?) {
    if (value != null) {
      coroutineScope {
        saveNewsInDb(value as ArrayList<Result>)
        newsListLiveData.postValue(value)
      }
    }
  }

}