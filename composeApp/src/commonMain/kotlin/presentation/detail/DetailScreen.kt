package presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.model.Character
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

data class DetailScreen(val id: Int) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel: DetailScreenModel = getScreenModel()

        val state by screenModel.state.collectAsState()

        screenModel.getCharacterDetail(id)

        when (state) {
            DetailState.Loading,
            is DetailState.ShowError -> Unit

            is DetailState.Content -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(24.dp)
                            .clickable {
                                navigator.pop()
                            },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )

                    DetailContent(
                        character = (state as DetailState.Content).character
                    )
                }
            }
        }
    }

    @Composable
    private fun DetailContent(
        character: Character,
    ) {
        KamelImage(
            resource = asyncPainterResource(data = character.imageUrl.orEmpty()),
            contentDescription = character.imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .heightIn(max = 400.dp)
                .widthIn(max = 400.dp)
                .padding(horizontal = 60.dp)
                .clip(RoundedCornerShape(12.dp)),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = character.name.orEmpty().plus(" (${character.house})"),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = character.description.orEmpty(),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )
    }
}