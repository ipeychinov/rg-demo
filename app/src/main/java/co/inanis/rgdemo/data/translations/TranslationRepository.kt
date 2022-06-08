package co.inanis.rgdemo.data.translations

import co.inanis.rgdemo.data.remote.ApiResponse
import co.inanis.rgdemo.data.remote.utils.Either
import co.inanis.rgdemo.data.sources.LocalTranslationSource
import co.inanis.rgdemo.data.sources.RemoteTranslationSource
import co.inanis.rgdemo.model.Translation
import co.inanis.rgdemo.model.TranslationType

interface TranslationRepository {

    suspend fun translate(
        to: TranslationType,
        text: String,
    ): Either<String?, ApiResponse<Translation>>

    suspend fun getTranslationHistory(): List<Translation>

}

class TranslationRepositoryImpl(
    private val localSource: LocalTranslationSource,
    private val remoteSource: RemoteTranslationSource,
) : TranslationRepository {

    override suspend fun translate(
        to: TranslationType,
        text: String
    ): Either<String?, ApiResponse<Translation>> = when (to) {
        TranslationType.SINDARIN -> remoteSource.translateToSindarin(text)
        TranslationType.QUENYA -> remoteSource.translateToQuenya(text)
    }.also {
        if (it.isSuccess) {
            localSource.saveTranslation(it.success.contents)
        }
    }

    override suspend fun getTranslationHistory(): List<Translation> =
        localSource.getTranslationHistory()

}