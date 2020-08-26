package net.rafaeltoledo.kotlin.network

import io.ktor.client.HttpClient
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.get
import net.rafaeltoledo.kotlin.data.domain.Listing
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object RedditClient {

    private const val API_HOST = "http://api.reddit.com"

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun fetchSubreddit(subreddit: String): Listing {
        return client.get("$API_HOST/r/$subreddit/hot")
    }
}