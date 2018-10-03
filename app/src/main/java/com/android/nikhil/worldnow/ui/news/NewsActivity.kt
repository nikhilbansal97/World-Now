package com.android.nikhil.worldnow.ui.news

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.android.nikhil.worldnow.R
import com.android.nikhil.worldnow.domain.entity.Section
import com.android.nikhil.worldnow.ui.base.BaseActivity
import com.android.nikhil.worldnow.ui.filter.SectionFilterAdapter
import com.android.nikhil.worldnow.utils.NewsItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class NewsActivity : BaseActivity<NewsViewModal>(), NewsItemClickListener {

    override fun getViewModelClass(): Class<NewsViewModal> = NewsViewModal::class.java

    private val TAG = NewsActivity::class.java.simpleName
    private lateinit var adapter: NewsRecyclerAdapter
    private lateinit var sectionAdapter: SectionFilterAdapter
    @Inject
    lateinit var mViewModel: NewsViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        filterRecyclerView.layoutManager = LinearLayoutManager(this)

        // Observe the MutableLiveData in the ViewHolder.
        mViewModel.getNewsData().observe(this, Observer { list ->
            run {
                if (list != null) {
                    // Get the ViewHolder for this activity
                    adapter = NewsRecyclerAdapter(this, this)
                    adapter.updateResults(list)
                    newsRecyclerView.adapter = adapter
                } else {
                    Log.d(TAG, "Updated list is null")
                }
            }
        })

        mViewModel.sectionItems.observe(this, Observer {
            if (it != null) {
                sectionAdapter = SectionFilterAdapter(this, it, this::onSectionCheckChange)
                filterRecyclerView.adapter = sectionAdapter
            }
        })

        mViewModel.getNews()
    }

    private fun onSectionCheckChange(section: Section, isChecked: Boolean) {
        with(mViewModel.selectedSections) {
            if (isChecked) add(section)
            else remove(section)
            updateFilteredResults(this)
        }
    }

    private fun updateFilteredResults(selectedSections: MutableSet<Section>?) {
        val itemsToUpdate = mViewModel.getNewsData().value?.filter {
            selectedSections?.asSequence()?.map {
                it.name
            }?.contains(it.sectionName) ?: false
        }

        if (itemsToUpdate != null) {
            adapter.updateResults(itemsToUpdate)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_filter -> openFilterMenu()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openFilterMenu() {
        with(drawerLayout) {
            if (isDrawerOpen(Gravity.START)) closeDrawer(Gravity.START)
            else openDrawer(Gravity.START)
        }
    }

    // This function is called when a news item is clicked.
    override fun onNewsClicked(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
