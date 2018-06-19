package com.android.nikhil.worldnow

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.nikhil.worldnow.network.ApiClient
import com.android.nikhil.worldnow.network.NewsInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), NewsItemClickListener {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var mViewModel: NewsViewModal
    private lateinit var adapter: NewsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the ViewHolder for this activity
        mViewModel = ViewModelProviders.of(this).get(NewsViewModal::class.java)
        adapter = NewsRecyclerAdapter(this, this,  mViewModel.getNewsData().value)
        newsRecyclerView.adapter = adapter
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Observe the MutableLiveData in the ViewHolder.
        mViewModel.getNewsData().observe(this, Observer { list ->
            run {
                if (list != null) adapter.swapNewsData(list)
                else Log.d(TAG, "Updated list is null")
        } })

        // The Retrofit call
        val apiClient = ApiClient.getClient()
        if (apiClient != null ) {
            val apiInterface = apiClient.create(NewsInterface::class.java)
            val callNews = apiInterface.getNews("test")
            callNews.enqueue(object: Callback<MainResponse> {
                override fun onResponse(call: Call<MainResponse>?, mainResponse: retrofit2.Response<MainResponse>?) {
                    val newsResponse = mainResponse?.body()
                    val body = newsResponse?.response
                    if (body?.results != null) mViewModel.updateNewsDate(body.results)
                    else Log.d(TAG, "results are null")
                }
                override fun onFailure(call: Call<MainResponse>?, t: Throwable?) {
                    Log.d(TAG, t?.localizedMessage)
                }
            })
        }
    }

    // This function is called when a news item is clicked.
    override fun onNewsClicked(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
