package co.inanis.rgdemo.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.inanis.rgdemo.CoroutineRule
import co.inanis.rgdemo.data.sources.FakeLocalTranslationSource
import co.inanis.rgdemo.data.sources.FakeRemoteTranslationSource
import co.inanis.rgdemo.data.translations.TranslationRepositoryImpl
import co.inanis.rgdemo.getOrAwaitValue
import co.inanis.rgdemo.model.TranslationType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TranslationViewModelTest {

    private lateinit var translationViewModel: TranslationViewModel

    @Before
    fun initViewModel() {
        val repository = TranslationRepositoryImpl(
            FakeLocalTranslationSource(),
            FakeRemoteTranslationSource(),
        )

        translationViewModel = TranslationViewModel(repository)
    }

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Test
    fun `test translate`() = runTest {
        translationViewModel.translate(TranslationType.SINDARIN, "Text")

        val translation = translationViewModel.translation.getOrAwaitValue()

        assertThat(translation).isNotNull()
        assertThat(translation?.type).isEqualTo(TranslationType.SINDARIN)
        assertThat(translation?.text).isEqualTo("Text")
    }

    @Test
    fun `test load history empty`() = runTest {
        val allTranslations = translationViewModel.history.getOrAwaitValue()

        assertThat(allTranslations).isNotNull()
        assertThat(allTranslations).isEmpty()
    }

    @Test
    fun `test load history`() = runTest {
        translationViewModel.translate(TranslationType.SINDARIN, "Text")

        val translation = translationViewModel.translation.getOrAwaitValue()
        val allTranslations = translationViewModel.history.getOrAwaitValue()

        assertThat(allTranslations).isNotNull()
        assertThat(allTranslations).isNotEmpty()
        assertThat(allTranslations).contains(translation)
    }

    @Test
    fun `test refresh history`() = runTest {
        val emptyTranslations = translationViewModel.history.getOrAwaitValue()

        assertThat(emptyTranslations).isNotNull()
        assertThat(emptyTranslations).isEmpty()

        translationViewModel.translate(TranslationType.SINDARIN, "Text")
        val translation = translationViewModel.translation.getOrAwaitValue()

        translationViewModel.refreshHistory()
        val refreshedTranslations = translationViewModel.history.getOrAwaitValue()

        assertThat(refreshedTranslations).isNotNull()
        assertThat(refreshedTranslations).isNotEmpty()
        assertThat(refreshedTranslations).contains(translation)
    }

}