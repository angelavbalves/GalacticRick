import flows.home.data.Endpoints
import flows.home.data.repositories.RickAndMortyRepository
import flows.home.domain.mappers.toDomain
import flows.home.domain.models.characters.domain.Character
import flows.home.network.ApiService

class RickAndMortyRepositoryImpl(private val endpoints: Endpoints) : RickAndMortyRepository {

    override suspend fun getCharacters(name: String?, status: String?): List<Character> {
        val response = endpoints.getCharacters(name, status)
        return response.characters.map { it.toDomain() }
    }

    override suspend fun getCharacterDetails(id: String): Character {
        val response = endpoints.getDetailsCharacter(id)
        return response.toDomain()
    }
}