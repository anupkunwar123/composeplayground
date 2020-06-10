package com.anupkunwar.composeplayground

import androidx.compose.Model
import com.anupkunwar.composeplayground.model.Post

sealed class Screen {
    object MainScreen : Screen()
    class DetailScreen(val post: Post) : Screen()
}

@Model
object Navigation {
    var currentScreen: Screen = Screen.MainScreen
}


fun navigateTo(screen: Screen) {
    Navigation.currentScreen = screen
}
