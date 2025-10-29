package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma

import cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.ktor.UserApiService
import cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.sqldelight.Database
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

val appModule = module {
    single { UserApiService() }
    single { Database(get()) }
    single { UserRepository(get(), get()) }
}

val winModule = module {}

val androidModule = module {}

fun initKoin() = startKoin {
    modules(appModule)
}