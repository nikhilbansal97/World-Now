package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.data.model.main_news.Result
import com.android.nikhil.worldnow.databinding.ActivityMainBinding
import com.android.nikhil.worldnow.utils.NewsItemClickListener
import com.android.nikhil.worldnow.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.newsRecyclerView
import javax.inject.Inject

class NewsActivity : BaseActivity<NewsViewModal>(), NewsItemClickListener {

    override fun getViewModelClass(): Class<NewsViewModal> = NewsViewModal::class.java

    private val TAG = NewsActivity::class.java.simpleName
    private lateinit var adapter: NewsRecyclerAdapter
    @Inject lateinit var mViewModel: NewsViewModal
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set Main data binding view
        dataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        dataBinding.let {
            it.viewModel = mViewModel
            it.setLifecycleOwner(this@NewsActivity)
        }

        setupNews()
    }

    override fun onResume() {
        super.onResume()
        dataBinding.viewModel?.getNews()
    }

    fun setupNews(){
        val viewModel = dataBinding.viewModel
        if (viewModel != null){
            // Get the ViewHolder for this activity
            adapter = NewsRecyclerAdapter(this, this, ArrayList<Result>())
            dataBinding.newsRecyclerView.adapter = adapter
        }
    }

    // This function is called when a news item is clicked.
    override fun onNewsClicked(view : View, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
