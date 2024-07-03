package flows.home.data

import flows.home.data.repositories.RickAndMortyRepository
import flows.home.domain.models.characters.data.CharactersResponse
import flows.home.domain.models.characters.domain.Character
import flows.home.network.ApiService

internal class Service(
    private val repository: RickAndMortyRepository
): ApiService {

    override suspend fun getCharacters(name: String?, status: String?): CharactersResponse {
        return repository.getCharacters(name, status)
    }

    override suspend fun getCharacterDetails(id: String): Character {
        return repository.getCharacterDetails(id = id)
    }
}