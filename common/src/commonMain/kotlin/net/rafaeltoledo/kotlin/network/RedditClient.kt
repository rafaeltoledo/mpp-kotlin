package net.rafaeltoledo.kotlin.network

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import net.rafaeltoledo.kotlin.data.domain.Listing
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object RedditClient {

    private const val API_HOST = "https://api.reddit.com"
    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(nonStrictJson)
        }
    }

    suspend fun fetchSubreddit(subreddit: String): Listing {
        return client.get("$API_HOST/r/$subreddit/hot")
    }
}