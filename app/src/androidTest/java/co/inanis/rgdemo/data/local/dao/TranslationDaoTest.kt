package co.inanis.rgdemo.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.inanis.rgdemo.data.local.AppDatabase
import co.inanis.rgdemo.model.Translation
import co.inanis.rgdemo.model.TranslationType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class TranslationDaoTest {

    private lateinit var db: AppDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).build()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsert() = runTest {
        val translation = Translation(
            type = TranslationType.SINDARIN,
            text = "The world is changed; I can feel it in the water, I can feel it in the earth, I can smell it in the air.",
            translated = "I ambar na- changed; im tur- feel ha in i nen,  im tur- feel ha in i coe,  im tur- smell ha in i gwilith.",
        )

        val insertResult = db.translationDao().insertTranslation(translation)

        assertThat(insertResult).isNotEqualTo(-1L)

        translation.id = insertResult.toInt()

        val allTranslations = db.translationDao().fetchTranslationHistory()

        assertThat(allTranslations).contains(translation)
    }

    @Test
    fun testFetchTranslationHistory() = runTest {
        val translation = Translation(
            type = TranslationType.SINDARIN,
            text = "The world is changed; I can feel it in the water, I can feel it in the earth, I can smell it in the air.",
            translated = "I ambar na- changed; im tur- feel ha in i nen,  im tur- feel ha in i coe,  im tur- smell ha in i gwilith.",
        )

        db.translationDao().insertTranslation(translation).also {
            translation.id = it.toInt()
        }

        val allTranslations = db.translationDao().fetchTranslationHistory()

        assertThat(allTranslations).hasSize(1)
        assertThat(allTranslations).contains(translation)
    }

    @Test
    fun testFetchTranslationHistoryEmpty() = runTest {
        val allTranslations = db.translationDao().fetchTranslationHistory()

        assertThat(allTranslations).isEmpty()
    }

}