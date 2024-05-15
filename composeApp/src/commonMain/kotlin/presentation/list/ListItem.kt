package presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.Character
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ListItem(
    character: Character,
    onCharacterClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onCharacterClick(character.id ?: 1)
            }
    ) {
        KamelImage(
            resource = asyncPainterResource(data = character.imageUrl.orEmpty()),
            contentDescription = "Character Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3 / 4f),
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(8.dp),
            text = character.name.orEmpty(),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}