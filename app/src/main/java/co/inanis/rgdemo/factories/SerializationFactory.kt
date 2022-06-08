package co.inanis.rgdemo.factories

import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val serializationModule = module {

    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single { GsonConverterFactory.create(get()) }

}