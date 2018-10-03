package com.android.nikhil.worldnow.domain.entity

import com.google.gson.annotations.SerializedName

data class MainResponse(
        @SerializedName("response")
        val response: NewsResponse
)