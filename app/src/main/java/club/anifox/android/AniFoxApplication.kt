package club.anifox.android

import android.app.Application
import club.anifox.android.di.appModule
import club.anifox.android.di.commonModule
import club.anifox.android.di.dataModule
import club.anifox.android.di.networkModule
import club.anifox.android.di.useCaseModule
import club.anifox.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AniFoxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(this@AniFoxApplication)
            modules(
                commonModule(true, applicationContext),
                appModule,
                networkModule,
                useCaseModule,
                viewModelModule,
                dataModule,
            )
        }
    }
}
