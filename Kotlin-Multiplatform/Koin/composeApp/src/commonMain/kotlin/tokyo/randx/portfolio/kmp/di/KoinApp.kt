package tokyo.randx.portfolio.kmp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initKoin(configuration: KoinAppDeclaration? = null) {
    startKoin {
        printLogger()
        includes(configuration)
        modules(appModule)
    }
}