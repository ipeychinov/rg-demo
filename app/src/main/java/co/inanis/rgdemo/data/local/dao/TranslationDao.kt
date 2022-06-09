package co.inanis.rgdemo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.inanis.rgdemo.model.Translation

@Dao
interface TranslationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslation(data: Translation): Long

    @Query("select * from translations")
    suspend fun fetchTranslationHistory(): List<Translation>

}