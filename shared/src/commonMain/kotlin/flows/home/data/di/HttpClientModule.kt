package flows.home.data.di

import RickAndMortyRepositoryImpl
import flows.home.data.Endpoints
import flows.home.data.Service
import flows.home.data.repositories.RickAndMortyRepository
import flows.home.network.ApiService
import flows.home.network.KtorHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal fun configureDI() : DI {

    return DI {
        bindSingleton<HttpClient>() {
            HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json { ignoreUnknownKeys = true })
                }
                install(Logging) {
                    level = LogLevel.BODY
                }
            }
        }

        bindSingleton<KtorHttpClient> {
            KtorHttpClient(
                instance()
            )
        }

        bindSingleton<RickAndMortyRepository> {
            RickAndMortyRepositoryImpl(instance())
        }

        bindSingleton<ApiService> {
            Service(instance())
        }
    }
}
