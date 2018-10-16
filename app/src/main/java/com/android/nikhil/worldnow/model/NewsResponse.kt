package com.android.nikhil.worldnow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponse(@SerializedName("response") @Expose var response: Response? = null)
