package co.inanis.rgdemo.factories

import co.inanis.rgdemo.data.remote.Api
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val apiModule = module {

    single {
        OkHttpClient()
            .newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single<Api> {
        Retrofit.Builder()
            .baseUrl(Api.ENDPOINT)
            .client(get())
            .addConverterFactory(get())
            .build()
            .create(Api::class.java)
    }

}