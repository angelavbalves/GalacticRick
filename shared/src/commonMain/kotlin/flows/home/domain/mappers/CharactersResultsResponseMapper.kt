package flows.home.domain.mappers

import flows.home.domain.models.characters.data.CharacterOriginResponse
import flows.home.domain.models.characters.data.CharacterStatusResponse
import flows.home.domain.models.characters.data.CharactersResultsResponse
import flows.home.domain.models.characters.domain.Character
import flows.home.domain.models.characters.domain.CharacterOrigin
import flows.home.domain.models.characters.domain.CharacterStatus


fun CharactersResultsResponse.toDomain() : Character {
    return Character(
        id = id,
        characterName = characterName,
        status = status.toDomain(),
        specie = specie,
        gender = gender,
        origin = origin.toDomain(),
        imageUrl = imageUrl,
        episodes = episodes
    )
}

fun CharacterStatusResponse.toDomain() : CharacterStatus {
    return when (this) {
        CharacterStatusResponse.Alive -> CharacterStatus.Alive
        CharacterStatusResponse.Dead -> CharacterStatus.Dead
        CharacterStatusResponse.Unknown -> CharacterStatus.Unknown
    }
}

fun CharacterOriginResponse.toDomain() : CharacterOrigin {
    return CharacterOrigin(
        originName = originName
    )
}
