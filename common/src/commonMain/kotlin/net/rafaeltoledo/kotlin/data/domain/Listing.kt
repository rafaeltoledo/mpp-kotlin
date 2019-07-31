package net.rafaeltoledo.kotlin.data.domain

import kotlinx.serialization.Decoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonInput
import kotlinx.serialization.json.JsonObject

@Serializable(with = ListingSerializer::class)
data class Listing(
    val modhash: String,
    val dist: Int,
    val children: List<Topic>,
    val after: String?,
    val before: String?
)

@Serializer(forClass = Listing::class)
object ListingSerializer : KSerializer<Listing> {

    override fun deserialize(decoder: Decoder): Listing {
        if (decoder is JsonInput) {
            val json = decoder.decodeJson()
            (json.jsonObject["data"] as JsonObject).let {
                return Listing(
                    it.getPrimitive("modhash").content,
                    it.getPrimitive("dist").int,
                    it.getArray("children").map {
                        Json.parse(Topic.serializer(), it.toString())
                    },
                    it.getPrimitive("after").contentOrNull,
                    it.getPrimitive("before").contentOrNull
                )
            }
        }

        throw IllegalArgumentException("JsonInput expected")
    }
}