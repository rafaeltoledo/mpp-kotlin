package net.rafaeltoledo.kotlin.data.domain

import kotlinx.serialization.Decoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonInput
import kotlinx.serialization.json.JsonObject

@Serializable(with = TopicSerializer::class)
data class Topic(
    val title: String,
    val subreddit: String
)

@Serializer(forClass = Topic::class)
object TopicSerializer : KSerializer<Topic> {

    override fun deserialize(decoder: Decoder): Topic {
        if (decoder is JsonInput) {
            val json = decoder.decodeJson()
            (json.jsonObject["data"] as JsonObject).let {
                return Topic(
                    it.getPrimitive("title").content,
                    it.getPrimitive("subreddit").content
                )
            }
        }

        throw IllegalArgumentException("JsonInput expected")
    }
}