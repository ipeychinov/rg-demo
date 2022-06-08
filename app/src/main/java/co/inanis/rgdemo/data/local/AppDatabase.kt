package co.inanis.rgdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.inanis.rgdemo.data.local.converters.TranslationTypeConverter
import co.inanis.rgdemo.data.local.dao.TranslationDao
import co.inanis.rgdemo.model.Translation

@Database(
    entities = [
        Translation::class,
    ],
    version = AppDatabase.VERSION,
)
@TypeConverters(
    TranslationTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun translationDao(): TranslationDao

    companion object {
        const val VERSION = 1
    }

}