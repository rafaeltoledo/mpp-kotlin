package net.rafaeltoledo.kotlin.data.repository

import net.rafaeltoledo.kotlin.data.domain.Listing
import net.rafaeltoledo.kotlin.network.RedditClient

object RedditRepository {

    suspend fun fetch(subreddit: String = "kotlin"): Listing {
        return RedditClient.fetchSubreddit(subreddit)
    }
}