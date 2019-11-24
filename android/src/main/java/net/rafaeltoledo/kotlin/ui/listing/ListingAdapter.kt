package net.rafaeltoledo.kotlin.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.rafaeltoledo.kotlin.data.domain.Listing
import net.rafaeltoledo.kotlin.data.domain.Topic

class ListingAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val items = mutableListOf<Topic>()
    private var last: Listing? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(android.R.layout.simple_list_item_2, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].run {
            holder.title.text = title
            holder.author.text = subreddit
        }
    }

    fun add(listing: Listing) {
        last = listing
        items.addAll(listing.children)
        notifyItemRangeInserted(items.size - listing.children.size, listing.children.size)
    }

    fun restore(savedInstanceState: Bundle) { }

    fun save(outState: Bundle) { }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView = view.findViewById(android.R.id.text1)
    val author: TextView = view.findViewById(android.R.id.text2)
}