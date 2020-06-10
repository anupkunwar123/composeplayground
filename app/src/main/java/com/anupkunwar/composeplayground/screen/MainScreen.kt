package com.anupkunwar.composeplayground.screen

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.anupkunwar.composeplayground.*
import com.anupkunwar.composeplayground.R
import com.anupkunwar.composeplayground.data.PostRepository
import com.anupkunwar.composeplayground.model.Post
import com.anupkunwar.composeplayground.ui.divider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun MainScreen(postRepository: PostRepository) {
    Column {
        TopAppBar(
            title = {
                Text(text = "News")
            },
            elevation = 0.dp
        )
        Stack {
            when (val result = getUiState(postRepository::getPost)) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Success -> {
                    Content(list = result.data)
                }
                else -> {
                    Snackbar(text = { "Error occured" })
                }
            }
        }
    }


}

@Preview
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)) {
        CircularProgressIndicator()
    }

}

@Composable
fun Content(list: List<Post>) {
    VerticalScroller {
        Column {
            for (post in list) {
                PostItem(
                    post = post
                )
                Divider(color = divider)
            }
        }
    }


}

@Composable
fun PostItem(post: Post) {
    Clickable(
        modifier = Modifier.ripple().padding(16.dp),
        onClick = { navigateTo(Screen.DetailScreen(post = post)) }) {
        Row {
            NewsImage(post.image, modifier = Modifier.preferredWidth(120.dp).preferredHeight(90.dp))
            Text(
                text = post.title,
                modifier = Modifier.weight(1f).padding(start = 16.dp),
                style = MaterialTheme.typography.h6
            )
        }


    }
}

@Composable
fun NewsImage(imageUrl: String, modifier: Modifier) {
    val context = ContextAmbient.current
    var bitmap: Bitmap? by state<Bitmap?> {
        null
    }


    if (bitmap != null) {
        Image(
            asset = bitmap!!.asImageAsset(),
            modifier = modifier,
            contentScale = ContentScale.FillWidth
        )

    } else {
        Image(
            asset = vectorResource(id = R.drawable.ic_placeholder),
            modifier = modifier,
            contentScale = ContentScale.FillWidth
        )

    }


    Glide.with(context).asBitmap().load(imageUrl).into(object : CustomTarget<Bitmap>() {
        override fun onLoadCleared(placeholder: Drawable?) {
        }

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            bitmap = resource
        }
    })
}