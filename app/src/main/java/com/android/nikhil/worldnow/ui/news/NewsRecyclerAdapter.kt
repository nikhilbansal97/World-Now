package com.android.nikhil.worldnow.ui.news

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.data.model.main_news.Result
import com.android.nikhil.worldnow.databinding.ListItemBinding
import com.android.nikhil.worldnow.ui.news.NewsRecyclerAdapter.NewsViewHolder
import com.android.nikhil.worldnow.utils.NewsItemClickListener

import kotlinx.android.synthetic.main.list_item.view.item_date
import kotlinx.android.synthetic.main.list_item.view.item_section
import kotlinx.android.synthetic.main.list_item.view.item_title

class NewsRecyclerAdapter(var context: Context,var listener: NewsItemClickListener, var list: ArrayList<Result>?):
        RecyclerView.Adapter<NewsViewHolder>() {

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (list != null) {
            holder.bindItem(list!![position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = DataBindingUtil
                .inflate<ListItemBinding>(LayoutInflater.from(parent.context),
                        R.layout.list_item, parent, false) as ListItemBinding
        return NewsViewHolder(itemBinding)
    }

    fun swapNewsData(updatedList: List<Result>) {
        if (list == null) list = ArrayList<Result>()
        else list?.clear()
        list?.addAll(updatedList)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(var view: ListItemBinding): RecyclerView.ViewHolder(view.root) {

        fun bindItem(news: Result){
            view.item = news
            view.executePendingBindings()
        }
    }
}