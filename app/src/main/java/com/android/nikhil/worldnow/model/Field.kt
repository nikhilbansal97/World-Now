package com.android.nikhil.worldnow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Field(@SerializedName("thumbnail") @Expose val thumbnail: String)
