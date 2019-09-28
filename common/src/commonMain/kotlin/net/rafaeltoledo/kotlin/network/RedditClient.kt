package net.rafaeltoledo.kotlin.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object RedditClient {

    private const val API_HOST = "https://api.reddit.com"

    private val client = HttpClient {  }

    suspend fun fetchSubreddit(subreddit: String): String {
        return client.get("$API_HOST/r/$subreddit/hot")
    }
}