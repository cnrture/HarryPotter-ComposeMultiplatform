package presentation.list

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.model.Character
import data.repository.CharactersRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListScreenModel(
    private val charactersRepository: CharactersRepository
) : StateScreenModel<ListState>(ListState.Loading) {

    fun getCharacters() = screenModelScope.launch {
        charactersRepository.getCharacters().onSuccess { characters ->
            if (characters.isEmpty()) {
                mutableState.update { ListState.Empty }
            } else {
                mutableState.update { ListState.Content(characters) }
            }
        }.onFailure { t ->
            mutableState.update { ListState.ShowError(t.message.orEmpty()) }
        }
    }
}

sealed interface ListState {
    data object Loading : ListState
    data object Empty : ListState
    data class Content(val characters: List<Character>) : ListState
    data class ShowError(val message: String?) : ListState
}