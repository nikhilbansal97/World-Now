package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.nikhil.worldnow.BuildConfig
import com.android.nikhil.worldnow.domain.entity.Result
import com.android.nikhil.worldnow.domain.entity.Section
import com.android.nikhil.worldnow.network.NewsInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashSet

class NewsViewModal @Inject constructor(
        private val newsApi: NewsInterface
) : ViewModel() {

    val sectionItems = MutableLiveData<List<Section>>()
    val selectedSections = HashSet<Section>()
    private val newsLiveData = MutableLiveData<ArrayList<Result>>()

    fun getNews() {
        newsApi.getNews(BuildConfig.ApiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { mainRes -> mainRes.response.results }
                .subscribe({ value ->
                    newsLiveData.postValue(value)
                    val sections = value.asSequence()
                            .map { Section(it.sectionName) }
                            .distinct()
                            .toList()
                    sectionItems.postValue(sections)
                    selectedSections.addAll(sections)
                }, { throwable ->
                    throwable.printStackTrace()
                })
    }

    fun getNewsData() = newsLiveData
}