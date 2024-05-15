package presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.model.Character
import harrypottercmp.composeapp.generated.resources.Res
import harrypottercmp.composeapp.generated.resources.harry_potter_logo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.detail.DetailScreen

data object ListScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel: ListScreenModel = getScreenModel()

        val state by screenModel.state.collectAsState()

        screenModel.getCharacters()

        when (state) {
            ListState.Loading,
            ListState.Empty,
            is ListState.ShowError -> Unit

            is ListState.Content -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(20.dp),
                        painter = painterResource(Res.drawable.harry_potter_logo),
                        contentDescription = "Harry Potter Logo"
                    )

                    ListContent(
                        list = (state as ListState.Content).characters,
                        onCharacterClick = { id ->
                            navigator.push(DetailScreen(id))
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun ListContent(
        list: List<Character>,
        onCharacterClick: (Int) -> Unit
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp)
        ) {
            items(list) {
                ListItem(
                    character = it,
                    onCharacterClick = onCharacterClick
                )
            }
        }
    }
}