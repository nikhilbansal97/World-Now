package com.android.nikhil.worldnow.news.list

import android.arch.lifecycle.MutableLiveData
import com.android.nikhil.worldnow.model.Result
import com.android.nikhil.worldnow.service.NewsService
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
* NewsRepository class is responsible for providing the news to the view model.
* First the local data will be provided and then it will be updated by the server's response.
*/

class NewsRepository @Inject constructor(private val newsService: NewsService) {

  private val realm = Realm.getDefaultInstance()
  val newsListLiveData = MutableLiveData<ArrayList<Result>>()

  /*
  * Main method exposed to the viewmodel to get the news.
  */
  suspend fun getNews(): ArrayList<Result> = coroutineScope {
    // As the below tasks are independent of each other, we can run them parallelly.
    val news = async { getNewsFromDb() }
    async { getNewsFromServer() }
    news.await()
  }

  /*
  * Get the news from Realm Database
  */
  private suspend fun getNewsFromDb(): ArrayList<Result> {
    val results = ArrayList<Result>()
    withContext(Dispatchers.IO) {
      realm.beginTransaction()
      val realmResults = realm.where(Result::class.java)
        .findAll()
      realmResults.filterNotNull()
        .forEach { results.add(it) }
      realm.commitTransaction()
    }
    return results
  }

  /*
  * Save the news in the Realm database
  */
  private suspend fun saveNewsInDb(newsList: ArrayList<Result>) {
    withContext(Dispatchers.IO) {
      val realm = Realm.getDefaultInstance()
      realm.beginTransaction()
      realm.insert(newsList)
      realm.commitTransaction()
    }
  }

  /*
  * Get the news from the server
  */
  private suspend fun getNewsFromServer() {
    withContext(Dispatchers.IO) {
      val news = newsService.getNews("BuildConfig.ApiKey")
        .await()
      processNews(news.response?.results)
    }
  }

  /*
  * Update the database and notify the viewmodel about the new news items received.
  */
  private suspend fun processNews(value: List<Result>?) {
    if (value != null) {
      saveNewsInDb(value as ArrayList<Result>)
      newsListLiveData.postValue(value)
    }
  }
}