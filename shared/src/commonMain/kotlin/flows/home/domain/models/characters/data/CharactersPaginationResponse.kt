package flows.home.domain.models.characters.data

import kotlinx.serialization.Serializable

@Serializable
data class CharactersPaginationResponse(
    val next: String? = null,
    val prev: String? = null,
)
