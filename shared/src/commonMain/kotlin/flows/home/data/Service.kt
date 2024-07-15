    package flows.home.data

    import flows.home.data.di.configureDI
    import flows.home.domain.models.characters.data.CharactersResponse
    import flows.home.domain.models.characters.data.CharactersResultsResponse
    import flows.home.network.ApiService
    import flows.home.network.KtorHttpClient
    import org.kodein.di.instance

    internal class Service: ApiService {

        private val ktorHttpClient: KtorHttpClient by configureDI().instance()

        override suspend fun getCharacters(name: String?, status: String?): CharactersResponse {
            val params = mapOf(
                "name" to name,
                "status" to status
            )
            return ktorHttpClient.makeRequest(
                endpoint = "/character",
                params = params,
                serializer = CharactersResponse.serializer()
            )
        }

        override suspend fun getCharacterDetails(id: String): CharactersResultsResponse {
            return ktorHttpClient.makeRequest(
                endpoint = "/character/$id",
                params = emptyMap(),
                serializer = CharactersResultsResponse.serializer()
            )
        }
    }