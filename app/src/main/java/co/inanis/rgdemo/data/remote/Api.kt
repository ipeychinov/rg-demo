package co.inanis.rgdemo.data.remote

import co.inanis.rgdemo.model.Translation
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("sindarin")
    suspend fun translateToSindarin(@Query(QUERY_TEXT) text: String): ApiResponse<Translation>

    @GET("quenya")
    suspend fun translateToQuenya(@Query(QUERY_TEXT) text: String): ApiResponse<Translation>

    companion object {
        private const val QUERY_TEXT = "text"

        const val ENDPOINT = "https://api.funtranslations.com/translate/"
    }

}