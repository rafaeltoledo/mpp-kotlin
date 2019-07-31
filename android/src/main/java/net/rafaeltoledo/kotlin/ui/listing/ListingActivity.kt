package net.rafaeltoledo.kotlin.ui.listing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import net.rafaeltoledo.kotlin.R

class ListingActivity : AppCompatActivity(R.layout.activity_listing) {

    private val viewModel: ListingViewModel by viewModels()
    private val list by lazy(mode = LazyThreadSafetyMode.NONE) { findViewById<RecyclerView>(R.id.list) }

    private val adapter = ListingAdapter()
    private val layoutManager by lazy(mode = LazyThreadSafetyMode.NONE) { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.layoutManager = layoutManager
        list.adapter = adapter

        if (savedInstanceState == null) {
            viewModel.fetch()
        } else {
            layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable("LM"))
            adapter.restore(savedInstanceState)
        }

        viewModel.listing.observe(this, Observer {
            title = it.children[0].subreddit
            adapter.add(it)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        adapter.save(outState)
        outState.putParcelable("LM", layoutManager.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }
}