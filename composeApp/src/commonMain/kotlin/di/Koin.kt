package di

import data.repository.CharactersRepository
import data.source.HarryPotterService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import presentation.list.ListScreenModel
import presentation.detail.DetailScreenModel

val dataModule = module {
    single {
        HttpClient {
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    single<HarryPotterService> { HarryPotterService(get()) }
    single { CharactersRepository(get()) }
}

val screenModelsModule = module {
    factoryOf(::ListScreenModel)
    factoryOf(::DetailScreenModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            screenModelsModule,
        )
    }
}