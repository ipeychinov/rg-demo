package co.inanis.rgdemo.data.sources

import co.inanis.rgdemo.data.remote.Api
import co.inanis.rgdemo.data.remote.ApiResponse
import co.inanis.rgdemo.data.remote.utils.Either
import co.inanis.rgdemo.data.remote.utils.safeApiCall
import co.inanis.rgdemo.model.Translation
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface RemoteTranslationSource {

    suspend fun translateToSindarin(text: String): Either<String?, ApiResponse<Translation>>

    suspend fun translateToQuenya(text: String): Either<String?, ApiResponse<Translation>>

}

class RemoteTranslationSourceImpl(
    private val api: Api,
    private val gson: Gson,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RemoteTranslationSource {

    override suspend fun translateToSindarin(text: String): Either<String?, ApiResponse<Translation>> =
        safeApiCall(gson, dispatcher) {
            api.translateToSindarin(text)
        }

    override suspend fun translateToQuenya(text: String): Either<String?, ApiResponse<Translation>> =
        safeApiCall(gson, dispatcher) {
            api.translateToQuenya(text)
        }

}