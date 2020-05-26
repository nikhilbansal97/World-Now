package com.android.nikhil.worldnow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(

  @SerializedName("status")
  @Expose
  var status: String? = null,
  @SerializedName("userTier")
  @Expose
  var userTier: String? = null,
  @SerializedName("total")
  @Expose
  var total: Int? = null,
  @SerializedName("startIndex")
  @Expose
  var startIndex: Int? = null,
  @SerializedName("pageSize")
  @Expose
  var pageSize: Int? = null,
  @SerializedName("currentPage")
  @Expose
  var currentPage: Int? = null,
  @SerializedName("pages")
  @Expose
  var pages: Int? = null,
  @SerializedName("orderBy")
  @Expose
  var orderBy: String? = null,
  @SerializedName("results")
  @Expose
  var results: List<Result>? = null

)
