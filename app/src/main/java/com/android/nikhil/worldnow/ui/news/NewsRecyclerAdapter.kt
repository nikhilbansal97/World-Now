package com.android.nikhil.worldnow.ui.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.ui.news.NewsRecyclerAdapter.NewsViewHolder
import com.android.nikhil.worldnow.utils.Result
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_article_card.*

class NewsRecyclerAdapter(var context: Context, var resultsList: ArrayList<Result>?):
        RecyclerView.Adapter<NewsViewHolder>() {

    override fun getItemCount(): Int {
        return if (resultsList != null) resultsList!!.size else 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (resultsList != null) holder.bind(resultsList!!.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_article_card, parent, false))
    }

    fun swapNewsData(updatedList: ArrayList<Result>) {
        if (resultsList == null) {
            resultsList = ArrayList()
        } else {
            resultsList?.clear()
        }

        resultsList?.addAll(updatedList)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer {

        lateinit var result: Result

        fun bind(result: Result) {
            this.result = result

            /*
                TODO
                To include an article image, the service query to The Guardian API has to
                include a parameter called 'show-fields' with the value 'thumbnail',
                e.g. show-fields=thumbnail. This will return a response with results that contain
                the fields which in turn contains the thumbnail of the article. Use this link with
                your API key to see the response: http://content.guardianapis.com/search?api-key=YOUR_API_KEY_HERE&show-fields=thumbnail

                Refactoring the service to include the thumbnail will allow us to get the thumbnail
                in the result data class and use it in the app using the line below:

                Glide.with(context).load(result.thumbnail()).into(articleCardImageView)
            */

            articleCardTitleTextView.text = result.webTitle
            articleCardDateTextView.text = result.webPublicationDate

            containerView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(result.webUrl)
            context.startActivity(intent)
        }
    }

}