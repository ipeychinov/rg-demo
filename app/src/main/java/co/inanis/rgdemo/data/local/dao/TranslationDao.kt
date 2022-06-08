package co.inanis.rgdemo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.inanis.rgdemo.model.Translation

@Dao
interface TranslationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslation(data: Translation)

    @Query("select * from translations order by timestamp desc")
    suspend fun fetchTranslationHistory(): List<Translation>

}