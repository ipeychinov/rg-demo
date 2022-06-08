package co.inanis.rgdemo.factories

import androidx.room.Room
import co.inanis.rgdemo.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "Translations.db")
            .build()
    }

    single { get<AppDatabase>().translationDao() }

}