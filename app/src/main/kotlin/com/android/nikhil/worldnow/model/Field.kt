package com.android.nikhil.worldnow.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Field : RealmModel {
  @SerializedName("thumbnail")
  var thumbnail: String = ""
}
