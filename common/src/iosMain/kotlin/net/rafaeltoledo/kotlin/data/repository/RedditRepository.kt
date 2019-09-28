package net.rafaeltoledo.kotlin.data.repository

import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import net.rafaeltoledo.kotlin.MainScope
import net.rafaeltoledo.kotlin.data.domain.Listing
import net.rafaeltoledo.kotlin.network.RedditClient

object RedditRepository {

    fun fetch(subreddit: String = "kotlin", completion: (Listing) -> Unit) {
        MainScope().launch {
            val response = RedditClient.fetchSubreddit(subreddit)
            completion(Json.parse(Listing.serializer(), response))
        }
    }
}
