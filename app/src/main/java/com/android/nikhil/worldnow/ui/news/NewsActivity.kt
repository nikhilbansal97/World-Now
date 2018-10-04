package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.ui.base.BaseActivity
import com.android.nikhil.worldnow.utils.NewsItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class NewsActivity : BaseActivity<NewsViewModal>(), NewsItemClickListener {

    private lateinit var adapter: NewsRecyclerAdapter
    @Inject lateinit var mViewModel: NewsViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsRecyclerAdapter(this, this, ArrayList())
        newsRecyclerView.adapter = adapter

        mViewModel.getNewsData().observe(this, Observer { list ->
            run {
                if (list != null) {
                    adapter.swapNewsData(list)
                } else {
                    Log.d(NewsActivity::class.java.simpleName, "Updated list is null")
                }
            }
        })

        mViewModel.getNews()
    }

    override fun onNewsClicked(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun getViewModelClass(): Class<NewsViewModal> = NewsViewModal::class.java

}