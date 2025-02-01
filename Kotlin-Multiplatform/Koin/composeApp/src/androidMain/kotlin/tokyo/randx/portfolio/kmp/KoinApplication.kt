package tokyo.randx.portfolio.kmp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import tokyo.randx.portfolio.kmp.di.initKoin

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@KoinApplication)
            androidLogger()
        }
    }
}
