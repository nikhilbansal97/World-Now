package com.android.nikhil.worldnow.utils

import android.view.View

interface NewsItemClickListener {
    fun onNewsClicked(view: View, url: String)
}