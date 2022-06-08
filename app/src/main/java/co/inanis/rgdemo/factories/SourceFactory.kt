package co.inanis.rgdemo.factories

import co.inanis.rgdemo.data.sources.LocalTranslationSource
import co.inanis.rgdemo.data.sources.LocalTranslationSourceImpl
import co.inanis.rgdemo.data.sources.RemoteTranslationSource
import co.inanis.rgdemo.data.sources.RemoteTranslationSourceImpl
import org.koin.dsl.module

val sourceModule = module {

    single<LocalTranslationSource> {
        LocalTranslationSourceImpl(get())
    }

    single<RemoteTranslationSource> {
        RemoteTranslationSourceImpl(
            get(),
            get(),
        )
    }

}