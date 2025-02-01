package tokyo.randx.portfolio.kmp.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import tokyo.randx.portfolio.kmp.data.TransactionRepository
import tokyo.randx.portfolio.kmp.data.TransactionRepositoryImpl
import tokyo.randx.portfolio.kmp.getPlatform
import tokyo.randx.portfolio.kmp.viewmodel.TransactionViewModel

val appModule = module {
    singleOf(::TransactionRepositoryImpl) { bind<TransactionRepository>() }
    viewModelOf(::TransactionViewModel)
    factory { getPlatform(this) }
}