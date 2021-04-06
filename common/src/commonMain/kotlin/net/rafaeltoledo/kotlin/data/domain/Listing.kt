package net.rafaeltoledo.kotlin.data.domain

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = ListingSerializer::class)
data class Listing(
    val modhash: String,
    val dist: Int,
    val children: List<Topic>,
    val after: String?,
    val before: String?
)

object ListingSerializer : KSerializer<Listing> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Listing", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Listing {
        val jsonDecoder = decoder as JsonDecoder
        val json = jsonDecoder.decodeJsonElement()
        (json.jsonObject["data"] as JsonObject).let {
            return Listing(
                it.getValue("modhash").jsonPrimitive.content,
                it.getValue("dist").jsonPrimitive.int,
                it.getValue("children").jsonArray.map { innerJson ->
                    Json.decodeFromString(Topic.serializer(), innerJson.toString())
                },
                it.getValue("after").jsonPrimitive.contentOrNull,
                it.getValue("before").jsonPrimitive.contentOrNull
            )
        }
    }

    override fun serialize(encoder: Encoder, value: Listing) {
        TODO("Not yet implemented")
    }
}