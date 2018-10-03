package com.android.nikhil.worldnow.ui.news

import android.content.Context
import android.support.v7.util.DiffUtil.calculateDiff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.domain.entity.Result
import com.android.nikhil.worldnow.ui.news.NewsRecyclerAdapter.NewsViewHolder
import com.android.nikhil.worldnow.utils.NewsItemClickListener
import kotlinx.android.synthetic.main.list_item.view.*

class NewsRecyclerAdapter(
        private val context: Context,
        private val listener: NewsItemClickListener
) : RecyclerView.Adapter<NewsViewHolder>() {

    private val results = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = results[position]
        holder.apply {
            textViewTitle.text = currentNews.webTitle
            textViewSection.text = currentNews.sectionName
            textViewDate.text = currentNews.webPublicationDate
            view.setOnClickListener { _ -> listener.onNewsClicked(currentNews.webUrl) }
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun updateResults(results: List<Result>) {
        val diffResult = calculateDiff(ResultDiffCallback(this.results, results))
        this.results.clear()
        this.results.addAll(results)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NewsViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var textViewTitle = view.item_title as TextView
        var textViewSection = view.item_section as TextView
        var textViewDate = view.item_date as TextView
    }
}