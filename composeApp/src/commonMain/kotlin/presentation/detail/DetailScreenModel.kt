package presentation.detail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.model.Character
import data.repository.CharactersRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailScreenModel(
    private val charactersRepository: CharactersRepository
) : StateScreenModel<DetailState>(DetailState.Loading) {

    fun getCharacterDetail(id: Int) = screenModelScope.launch {
        charactersRepository.getCharacterDetail(id).onSuccess { character ->
            mutableState.update { DetailState.Content(character) }
        }.onFailure { t ->
            mutableState.update { DetailState.ShowError(t.message.orEmpty()) }
        }
    }
}

sealed interface DetailState {
    data object Loading : DetailState
    data class Content(val character: Character) : DetailState
    data class ShowError(val message: String?) : DetailState
}