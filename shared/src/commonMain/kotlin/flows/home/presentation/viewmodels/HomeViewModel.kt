package flows.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import flows.common.RequestResult.RequestResult
import flows.home.data.di.configureDI
import flows.home.data.repositories.RickAndMortyRepository
import flows.home.domain.models.characters.domain.Character
import flows.home.presentation.ui.mappers.toPresentation
import flows.home.presentation.ui.models.CharacterHomePresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance


open class HomeViewModel : ViewModel() {
    private val repository: RickAndMortyRepository by configureDI().instance()
    private val liveData = MutableStateFlow<RequestResult<List<CharacterHomePresentation>>>(RequestResult.Loading)
    val charactersState: StateFlow<RequestResult<List<CharacterHomePresentation>>> = liveData.asStateFlow()

    fun fetchCharacters() : StateFlow<RequestResult<List<CharacterHomePresentation>>> {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getCharacters(null, null).map { it.toPresentation() }
                liveData.value = RequestResult.Success(result)
            } catch (e: Exception) {
                liveData.value = RequestResult.Error(e)
            }
        }
        return charactersState
    }
}