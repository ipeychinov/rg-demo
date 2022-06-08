package co.inanis.rgdemo

import android.app.Application
import co.inanis.rgdemo.factories.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)

            modules(
                apiModule,
                dbModule,
                serializationModule,
                sourceModule,
                repositoryModule,
                vmModule,
            )
        }

    }

}