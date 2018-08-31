package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.utils.NewsItemClickListener
import com.android.nikhil.worldnow.ui.NewsRecyclerAdapter
import com.android.nikhil.worldnow.ui.base.BaseActivity
import com.android.nikhil.worldnow.utils.Result
import kotlinx.android.synthetic.main.activity_main.newsRecyclerView
import javax.inject.Inject

class NewsActivity : BaseActivity<NewsViewModal>(), NewsItemClickListener {

    override fun getViewModelClass(): Class<NewsViewModal> = NewsViewModal::class.java

    private val TAG = NewsActivity::class.java.simpleName
    private lateinit var adapter: NewsRecyclerAdapter
    @Inject lateinit var mViewModel: NewsViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the ViewHolder for this activity
        adapter = NewsRecyclerAdapter(this, this, ArrayList<Result>())
        newsRecyclerView.adapter = adapter
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Observe the MutableLiveData in the ViewHolder.
        mViewModel.getNewsData().observe(this, Observer { list ->
            run {
                if (list != null) adapter.swapNewsData(list)
                else Log.d(TAG, "Updated list is null")
            }
        })

        mViewModel.getNews()
    }

    // This function is called when a news item is clicked.
    override fun onNewsClicked(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
