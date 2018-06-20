package com.android.nikhil.worldnow

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

class NewsRecyclerAdapter(var context: Context,var listener: NewsItemClickListener , var list: ArrayList<Result>?):
        RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (list != null) {
            val currentNews = list!![position]
            holder.apply {
                textViewTitle.text = currentNews.webTitle
                textViewSection.text = currentNews.sectionName
                textViewDate.text = currentNews.webPublicationDate
                view.setOnClickListener { _ -> listener.onNewsClicked(currentNews.webUrl) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    fun swapNewsData(updatedList: ArrayList<Result>) {
        if (list == null) list = ArrayList<Result>()
        else list?.clear()
        list?.addAll(updatedList)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        var textViewTitle = view.item_title as TextView
        var textViewSection = view.item_section as TextView
        var textViewDate = view.item_date as TextView
    }
}