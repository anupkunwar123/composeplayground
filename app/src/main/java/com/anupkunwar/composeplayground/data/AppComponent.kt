package com.anupkunwar.composeplayground.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService

class AppComponent {
    companion object {
        val executor: ExecutorService by lazy {
            java.util.concurrent.Executors.newFixedThreadPool(4)
        }

        val mainThreadHandler by lazy {
            Handler(Looper.getMainLooper())
        }

    }

    val postRepository: PostRepository =
        PostRepositoryImpl(executorService = executor, mainThreadHandler = mainThreadHandler)
}

