package com.android.nikhil.worldnow.ui.news

import android.support.v7.util.DiffUtil
import com.android.nikhil.worldnow.domain.entity.Result

class ResultDiffCallback(
        private val old: List<Result>,
        private val new: List<Result>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex] == new[newIndex]
    }

    override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex] == new[newIndex]
    }
}