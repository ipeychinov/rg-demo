package co.inanis.rgdemo.factories

import co.inanis.rgdemo.data.remote.serializares.TranslationSerializer
import co.inanis.rgdemo.model.Translation
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

val serializationModule = module {

    single {
        GsonBuilder()
            .setLenient()
            .registerTypeAdapter(Translation::class.java, get<TranslationSerializer>())
            .create()
    }

    single<Converter.Factory> { GsonConverterFactory.create(get()) }

    single { TranslationSerializer(Gson()) }

}