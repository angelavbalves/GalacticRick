package flows.home.domain.models.characters.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: CharactersPaginationResponse,
    @SerialName("results")
    val characters: List<CharactersResultsResponse>
)
