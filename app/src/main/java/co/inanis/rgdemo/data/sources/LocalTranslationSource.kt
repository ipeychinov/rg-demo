package co.inanis.rgdemo.data.sources

import co.inanis.rgdemo.data.local.dao.TranslationDao
import co.inanis.rgdemo.model.Translation

interface LocalTranslationSource {

    suspend fun saveTranslation(translation: Translation)

    suspend fun getTranslationHistory(): List<Translation>

}

class LocalTranslationSourceImpl(
    private val dao: TranslationDao
) : LocalTranslationSource {

    override suspend fun saveTranslation(translation: Translation) =
        dao.insertTranslation(translation)

    override suspend fun getTranslationHistory(): List<Translation> = dao.fetchTranslationHistory()

}