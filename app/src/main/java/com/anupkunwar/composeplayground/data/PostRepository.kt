package com.anupkunwar.composeplayground.data

import android.os.Handler
import com.anupkunwar.composeplayground.model.Post
import java.util.concurrent.ExecutorService

interface PostRepository {
    fun getPost(callback: Callback<List<Post>>)
}

interface Callback<T> {
    fun onResult(result: Result<T>)
}

class PostRepositoryImpl(
    private val executorService: ExecutorService,
    private val mainThreadHandler: Handler
) : PostRepository {
    override fun getPost(callback: Callback<List<Post>>) {
        executorService.execute {
            Thread.sleep(1000)
            mainThreadHandler.post {
                callback.onResult(result = Result.Success(data = POSTS))
            }
        }
    }
}

class BlockingRepositoryImpl(
) : PostRepository {
    override fun getPost(callback: Callback<List<Post>>) {
        callback.onResult(result = Result.Success(data = POSTS))

    }
}