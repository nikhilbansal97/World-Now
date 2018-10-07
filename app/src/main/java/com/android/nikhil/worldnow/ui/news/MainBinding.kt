package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.nikhil.worldnow.data.model.main_news.Result

object MainBinding {

    @BindingAdapter("app:newslist")
    @JvmStatic
    fun setNewsList(recyclerView: RecyclerView, news : MutableLiveData<List<Result>>){
        with(recyclerView.adapter as NewsRecyclerAdapter){
            if (news.value!= null) swapNewsData(news.value!!) else
                Log.e("RecyclerViewTag", "content empty")
        }
    }

}