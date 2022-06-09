package co.inanis.rgdemo.data.sources

import co.inanis.rgdemo.data.sources.LocalTranslationSource
import co.inanis.rgdemo.model.Translation

class FakeLocalTranslationSource: LocalTranslationSource {

    private val data = mutableListOf<Translation>()

    override suspend fun saveTranslation(translation: Translation) {
        data.add(translation)
    }

    override suspend fun getTranslationHistory(): List<Translation> = data

}