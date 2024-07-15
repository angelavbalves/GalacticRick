package flows.home.data

import flows.home.domain.models.characters.data.CharactersResponse
import flows.home.domain.models.characters.data.CharactersResultsResponse

interface Endpoints {
    suspend fun getCharacters(name: String?, status: String?): CharactersResponse
    suspend fun getDetailsCharacter(id: String): CharactersResultsResponse
}