package flows.home.network

import flows.home.domain.models.characters.data.CharactersResponse
import flows.home.domain.models.characters.data.CharactersResultsResponse

interface ApiService {
    suspend fun getCharacters(name: String?, status: String?) : CharactersResponse
    suspend fun getCharacterDetails(id: String) : CharactersResultsResponse
}