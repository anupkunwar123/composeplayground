package com.anupkunwar.composeplayground.screen

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.aspectRatio
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.unit.dp
import com.anupkunwar.composeplayground.Screen
import com.anupkunwar.composeplayground.model.Post
import com.anupkunwar.composeplayground.navigateTo

@Composable
fun DetailScreen(post: Post) {
    Column {
        TopAppBar(title = {
            Text(text = "News Detail")
        }, navigationIcon = {
            IconButton(onClick = { navigateTo(Screen.MainScreen) }) {
                Icon(Icons.Filled.ArrowBack)
            }
        },
            elevation = 0.dp
        )
        VerticalScroller(modifier = Modifier.fillMaxWidth()) {
            Column {
                NewsImage(imageUrl = post.image, modifier = Modifier.aspectRatio(16 / 9f))
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(all = 16.dp)
                )
                Text(
                    text = post.story,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
            }
        }
    }

}
