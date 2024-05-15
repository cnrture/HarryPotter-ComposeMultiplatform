package navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import presentation.list.ListScreen

@Composable
fun HarryPotterNavigator() {
    Navigator(ListScreen)
}