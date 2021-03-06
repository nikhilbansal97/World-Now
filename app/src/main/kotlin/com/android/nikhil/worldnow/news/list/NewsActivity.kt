package com.android.nikhil.worldnow.news.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.base.BaseActivity
import com.android.nikhil.worldnow.model.Result
import kotlinx.android.synthetic.main.activity_main.newsListProgressBar
import kotlinx.android.synthetic.main.activity_main.newsRecyclerView

class NewsActivity : BaseActivity<NewsViewModal>() {

  private lateinit var adapter: NewsRecyclerAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupNewsAdapter()
    viewModel.getNews()
    observeNewsData()
    observeError()
  }

  private fun setupNewsAdapter() {
    adapter = NewsRecyclerAdapter(this, ArrayList())
    newsRecyclerView.adapter = adapter
  }

  /*
  * Observe the viewmodel for any errors and update the UI accordingly.
  */
  private fun observeError() {
    viewModel.getErrorLiveData()
      .observe(this, Observer {
        newsListProgressBar.visibility = View.GONE
      })
  }

  /*
  * Observe the live data in the view model to update the UI accordingly.
  */
  private fun observeNewsData() {
    viewModel.getNewsData()
      .observe(this, Observer { list ->
        run {
          newsListProgressBar.visibility = View.GONE
          if (list != null) {
            adapter.swapNewsData(list as ArrayList<Result>)
          } else {
          }
        }
      })
  }

  override fun getViewModelClass(): Class<NewsViewModal> = NewsViewModal::class.java

}