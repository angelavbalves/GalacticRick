package flows.home.domain.models.characters.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResultsResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val characterName: String,
    @SerialName("status")
    val status: CharacterStatusResponse,
    @SerialName("species")
    val specie: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("origin")
    val origin: CharacterOriginResponse,
    @SerialName("url")
    val imageUrl: String,
    @SerialName("episode")
    val episodes: List<String>
)

@Serializable
data class CharacterOriginResponse(
    @SerialName("name")
    val originName: String
)

enum class CharacterStatusResponse(val status: String) {
    Alive("Alive"),
    Dead("Dead"),
    Unknown("Unknown")
}