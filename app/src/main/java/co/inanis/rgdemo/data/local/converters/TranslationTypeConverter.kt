package co.inanis.rgdemo.data.local.converters

import androidx.room.TypeConverter
import co.inanis.rgdemo.model.TranslationType

class TranslationTypeConverter {

    @TypeConverter
    fun fromString(data: String?) = data?.let { TranslationType.valueOf(it) }

    @TypeConverter
    fun toString(data: TranslationType?) = data?.toString()

}