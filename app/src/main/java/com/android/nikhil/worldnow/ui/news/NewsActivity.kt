package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class NewsActivity : BaseActivity<NewsViewModal>() {

    private lateinit var adapter: NewsRecyclerAdapter
    @Inject lateinit var mViewModel: NewsViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsRecyclerAdapter(this, ArrayList())
        newsRecyclerView.adapter = adapter

        mViewModel.getNewsData().observe(this, Observer { list ->
            run {
                newsListProgressBar.visibility = View.GONE

                if (list != null) {
                    adapter.swapNewsData(list)
                } else {
                    Log.d(NewsActivity::class.java.simpleName, "Updated resultsList is null")
                }
            }
        })

        mViewModel.getNews()
    }

    override fun getViewModelClass(): Class<NewsViewModal> = NewsViewModal::class.java

}