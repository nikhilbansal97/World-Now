package com.android.nikhil.worldnow.domain.entity

import com.google.gson.annotations.SerializedName

data class NewsResponse(
        @SerializedName("results")
        val results: ArrayList<Result>
)