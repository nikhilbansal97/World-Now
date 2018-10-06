package com.android.nikhil.worldnow.data.model.main_news

import com.google.gson.annotations.SerializedName

data class MainNews (
        @SerializedName("response")
        val response: NewsResponse
)

data class NewsResponse (
        @SerializedName("results")
        val results: ArrayList<Result>)

data class Result (
        @SerializedName("id")
        val id: String,
        @SerializedName("sectionName")
        val sectionName: String,
        @SerializedName("webPublicationDate")
        val webPublicationDate: String,
        @SerializedName("webTitle")
        val webTitle: String,
        @SerializedName("webUrl")
        val webUrl: String)