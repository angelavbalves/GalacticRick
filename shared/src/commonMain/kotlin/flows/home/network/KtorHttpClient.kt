package flows.home.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Url
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class KtorHttpClient(private val client: HttpClient) {

    private val baseUrl = "https://rickandmortyapi.com/api"
    suspend fun <T> makeRequest(endpoint: String, serializer: KSerializer<T>): T {
        val url = Url("$baseUrl$endpoint")
        val response: HttpResponse = client.get(url)
        return Json { ignoreUnknownKeys = true }.decodeFromString(serializer, response.body())
    }
}
