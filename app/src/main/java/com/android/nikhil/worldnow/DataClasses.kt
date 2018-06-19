package com.android.nikhil.worldnow

import com.google.gson.annotations.SerializedName

data class MainResponse (
        @SerializedName("response")
        val response: NewsResponse
)

data class NewsResponse (
        @SerializedName("status")
        val status: String,
        @SerializedName("userTier")
        val userTier: String,
        @SerializedName("total")
        val total: Int,
        @SerializedName("startIndex")
        val startIndex: Int,
        @SerializedName("pageSize")
        val pageSize: Int,
        @SerializedName("currentPage")
        val currentPage: Int,
        @SerializedName("pages")
        val pages: Int,
        @SerializedName("orderBy")
        val orderBy: String,
        @SerializedName("results")
        val results: ArrayList<Result>)

data class Result (
        @SerializedName("id")
        val id: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("sectionId")
        val sectionId: String,
        @SerializedName("sectionName")
        val sectionName: String,
        @SerializedName("webPublicationDate")
        val webPublicationDate: String,
        @SerializedName("webTitle")
        val webTitle: String,
        @SerializedName("webUrl")
        val webUrl: String,
        @SerializedName("apiUrl")
        val apiUrl: String,
        @SerializedName("isHosted")
        val isHosted: Boolean,
        @SerializedName("pillarId")
        val pillarId: String,
        @SerializedName("pillarName")
        val pillarName: String)