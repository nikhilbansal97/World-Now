package com.android.nikhil.worldnow.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("sectionId")
    @Expose
    var sectionId: String? = null,
    @SerializedName("sectionName")
    @Expose
    var sectionName: String? = null,
    @SerializedName("webPublicationDate")
    @Expose
    var webPublicationDate: String? = null,
    @SerializedName("webTitle")
    @Expose
    var webTitle: String? = null,
    @SerializedName("webUrl")
    @Expose
    var webUrl: String? = null,
    @SerializedName("apiUrl")
    @Expose
    var apiUrl: String? = null,
    @SerializedName("isHosted")
    @Expose
    var isHosted: Boolean? = null,
    @SerializedName("pillarId")
    @Expose
    var pillarId: String? = null,
    @SerializedName("pillarName")
    @Expose
    var pillarName: String? = null,
    @SerializedName("field")
    @Expose
    var field: Field? = null

)
