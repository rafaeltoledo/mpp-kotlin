package net.rafaeltoledo.kotlin.data.domain

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = TopicSerializer::class)
data class Topic(
    val title: String,
    val subreddit: String
)

object TopicSerializer : KSerializer<Topic> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Topic", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Topic {
        val jsonDecoder = decoder as JsonDecoder
        val json = jsonDecoder.decodeJsonElement()
        (json.jsonObject["data"] as JsonObject).let {
            return Topic(
                it.getValue("title").jsonPrimitive.content,
                it.getValue("subreddit").jsonPrimitive.content
            )
        }
    }

    override fun serialize(encoder: Encoder, value: Topic) {
        TODO("Not yet implemented")
    }
}