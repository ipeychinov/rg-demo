package co.inanis.rgdemo.data.remote.serializares

import co.inanis.rgdemo.model.Translation
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TranslationSerializer(private val gson: Gson) : JsonDeserializer<Translation?> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Translation? = try {
        json?.let { gson.fromJson(it, Translation::class.java) }?.also { it.id = 0 }
    }catch (e: Exception){
        null
    }
    
}