package net.rafaeltoledo.kotlin.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

object RedditClient {

    private val client = HttpClient {  }

    suspend fun fetchSubreddit(subreddit: String): String {
        return client.get("$API_HOST/r/$subreddit/hot")
    }

    private const val API_HOST = "https://api.reddit.com"
}