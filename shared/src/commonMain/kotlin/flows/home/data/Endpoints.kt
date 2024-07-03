package flows.home.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import flows.home.domain.models.characters.data.CharactersResponse
import flows.home.domain.models.characters.data.CharactersResultsResponse

interface Endpoints {
    // Characters
    @GET("api/character")
    suspend fun getCharacters(
        @Query("name") name: String?,
        @Query("status") status: String?
    ) : CharactersResponse

    @GET("api/character/{id}")
    suspend fun getDetailsCharacter(
        @Path("id") id: String,
    ) : CharactersResultsResponse
}