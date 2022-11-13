package dev.bltucker.androidtwirp

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.squareup.wire.Instant
import java.lang.reflect.Type

class InstantTypeConverter : JsonSerializer<Instant>, JsonDeserializer<Instant> {
    override fun serialize(src: Instant?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        if(src == null){
            return null
        }
        return JsonPrimitive(src.toString());
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Instant? {
        if(json == null){
            return null
        }

        return Instant.parse(json.asString)
    }

}