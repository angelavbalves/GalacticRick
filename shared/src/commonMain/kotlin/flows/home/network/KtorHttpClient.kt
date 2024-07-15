package flows.home.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class KtorHttpClient(private val client: HttpClient) {

    private val baseUrl = "https://rickandmortyapi.com/api"

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun <T> makeRequest(endpoint: String, params: Map<String, String?>, serializer: KSerializer<T>): T {
        val queryString = params.filterValues { it != null }.map { "${it.key}=${it.value}" }.joinToString("&")
        val url = "$baseUrl$endpoint?$queryString"
        val response: HttpResponse = client.get(url)
        return json.decodeFromString(serializer, response.body())
    }
}
