package com.android.nikhil.worldnow.domain.entity

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id")
        val id: String,
        @SerializedName("sectionName")
        val sectionName: String,
        @SerializedName("webPublicationDate")
        val webPublicationDate: String,
        @SerializedName("webTitle")
        val webTitle: String,
        @SerializedName("webUrl")
        val webUrl: String
) : Comparable<Result> {
    override fun compareTo(other: Result): Int {
        return this.id.compareTo(other.id)
    }
}