package flows.home.data.repositories

import flows.home.domain.models.characters.domain.Character

internal interface RickAndMortyRepository {
    suspend fun getCharacters(name: String?, status: String?) : List<Character>
    suspend fun getCharacterDetails(id: String) : Character
}