package co.inanis.rgdemo.data.remote.utils

import co.inanis.rgdemo.data.remote.ApiErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <R> safeApiCall(
    errorDeserializer: Gson,
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> R,
): Either<String?, R> = withContext(dispatcher) {
    try {
        Either.Success(apiCall.invoke())
    } catch (e: HttpException) {
        val error = errorDeserializer.fromJson(
            e.response()?.errorBody()?.charStream(),
            ApiErrorResponse::class.java,
        )

        Either.Failure(error.error.message)
    } catch (e: Exception) {
        Either.Failure(e.message)
    }
}