package co.inanis.rgdemo.data.sources

import co.inanis.rgdemo.data.remote.ApiResponse
import co.inanis.rgdemo.data.remote.utils.Either
import co.inanis.rgdemo.model.Translation
import co.inanis.rgdemo.model.TranslationType

class FakeRemoteTranslationSource : RemoteTranslationSource {

    var shouldReturnError: Boolean = false

    override suspend fun translateToSindarin(text: String): Either<String?, ApiResponse<Translation>> =
        if (!shouldReturnError) {
            Either.Success(
                ApiResponse(
                    Translation(
                        type = TranslationType.SINDARIN,
                        text = text,
                        translated = text.plus(", but in Sindarin")
                    )
                )
            )
        } else {
            Either.Failure("Test error")
        }

    override suspend fun translateToQuenya(text: String): Either<String?, ApiResponse<Translation>> =
        if (!shouldReturnError) {
            Either.Success(
                ApiResponse(
                    Translation(
                        type = TranslationType.QUENYA,
                        text = text,
                        translated = text.plus(", but in Quenya")
                    )
                )
            )
        } else {
            Either.Failure("Test error")
        }

}