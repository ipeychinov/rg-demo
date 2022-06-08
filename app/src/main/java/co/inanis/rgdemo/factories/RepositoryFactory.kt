package co.inanis.rgdemo.factories

import co.inanis.rgdemo.data.translations.TranslationRepository
import co.inanis.rgdemo.data.translations.TranslationRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<TranslationRepository> {
        TranslationRepositoryImpl(
            get(),
            get(),
        )
    }

}