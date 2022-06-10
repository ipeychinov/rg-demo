package co.inanis.rgdemo.data.translations

import androidx.test.ext.junit.runners.AndroidJUnit4
import co.inanis.rgdemo.data.sources.FakeLocalTranslationSource
import co.inanis.rgdemo.data.sources.FakeRemoteTranslationSource
import co.inanis.rgdemo.model.Translation
import co.inanis.rgdemo.model.TranslationType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class TranslationRepositoryTest {

    private lateinit var localSource: FakeLocalTranslationSource
    private lateinit var remoteSource: FakeRemoteTranslationSource
    private lateinit var repository: TranslationRepository

    @Before
    fun initRepository() {
        localSource = FakeLocalTranslationSource()
        remoteSource = FakeRemoteTranslationSource()

        repository = TranslationRepositoryImpl(
            localSource,
            remoteSource
        )
    }

    @Test
    fun testTranslate() = runTest {
        val result = repository.translate(TranslationType.SINDARIN, "Text")

        assertThat(result.isSuccess).isTrue()
        assertThat(result.success.contents.type).isEqualTo(TranslationType.SINDARIN)
        assertThat(result.success.contents.text).isEqualTo("Text")
    }

    @Test
    fun testTranslateDidSaveLocally() = runTest {
        val result = repository.translate(TranslationType.SINDARIN, "Text")

        val allTranslations = repository.getTranslationHistory()

        assertThat(allTranslations).hasSize(1)
        assertThat(allTranslations).contains(result.success.contents)
    }

    @Test
    fun testTranslateError() = runTest {
        remoteSource.shouldReturnError = true

        val result = repository.translate(TranslationType.SINDARIN, "Text")
        val allTranslations = repository.getTranslationHistory()

        assertThat(result.isFailure).isTrue()
        assertThat(result.failure).isNotNull()
        assertThat(result.failure).isEqualTo("Test error")
        assertThat(allTranslations).isEmpty()
    }

    @Test
    fun testGetTranslationHistory() = runTest {
        val translation = Translation(
            type = TranslationType.SINDARIN,
            text = "Text",
            translated = "Text, but in Sindarin",
        )

        localSource.saveTranslation(translation)

        val allTranslations = localSource.getTranslationHistory()

        assertThat(allTranslations).hasSize(1)
        assertThat(allTranslations).contains(translation)
    }

}