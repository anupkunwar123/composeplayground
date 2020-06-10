package com.anupkunwar.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.anupkunwar.composeplayground.data.POSTS
import com.anupkunwar.composeplayground.screen.Content
import com.anupkunwar.composeplayground.screen.DetailScreen
import com.anupkunwar.composeplayground.screen.MainScreen
import com.anupkunwar.composeplayground.ui.AppTheme
import com.anupkunwar.composeplayground.ui.LightColorPalette

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (application as MyApp).appComponent
        setContent {
            AppTheme(darkTheme = false, appComponent = appComponent) {
                when (val screen = Navigation.currentScreen) {
                    is Screen.MainScreen -> {
                        MainScreen(postRepository = appComponent.postRepository)
                    }
                    is Screen.DetailScreen -> {
                        DetailScreen(post = screen.post)
                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    ThemePreview {
        Content(list = POSTS)
    }

}

@Composable
fun ThemePreview(
    colorPalette: ColorPalette = LightColorPalette,
    children: @Composable() () -> Unit
) {
    MaterialTheme(colors = colorPalette, content = children)
}


