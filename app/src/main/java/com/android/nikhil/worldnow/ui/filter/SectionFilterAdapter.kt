package com.android.nikhil.worldnow.ui.filter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.domain.entity.Section
import kotlinx.android.synthetic.main.section_filter_item.view.*

typealias CheckBoxListener = (section: Section, checked: Boolean) -> Unit

class SectionFilterAdapter(
        private val context: Context,
        private val items: List<Section>,
        private val checkBoxListener: CheckBoxListener
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.section_filter_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = items[position]

        holder.sectionCheckbox.apply {
            text = section.name
            isChecked = section.checked
            setOnCheckedChangeListener { _, isChecked ->
                checkBoxListener.invoke(section, isChecked)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val sectionCheckbox: CheckBox = view.sectionCheckbox
}