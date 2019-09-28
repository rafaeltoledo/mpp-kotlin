package net.rafaeltoledo.kotlin.data.repository

import kotlinx.serialization.json.Json
import net.rafaeltoledo.kotlin.data.domain.Listing
import net.rafaeltoledo.kotlin.network.RedditClient

object RedditRepository {

    suspend fun fetch(subreddit: String = "kotlin"): Listing {
        val response = RedditClient.fetchSubreddit(subreddit)
        return Json.parse(Listing.serializer(), response)
    }
}