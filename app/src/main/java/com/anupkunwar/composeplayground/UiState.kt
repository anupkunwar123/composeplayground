package com.anupkunwar.composeplayground

import androidx.compose.*
import com.anupkunwar.composeplayground.data.Callback
import com.anupkunwar.composeplayground.data.Result

sealed class UiState<out T> {
    class Loading : UiState<Nothing>()
    data class Error(val exception: Exception) : UiState<Nothing>()
    data class Success<R>(val data: R) : UiState<R>()
}

typealias NetworkCall<T> = (Callback<T>) -> Unit


@Composable
fun <T> getUiState(networkCall: NetworkCall<T>): UiState<T> {
    var uiState: UiState<T> by state<UiState<T>> {
        UiState.Loading()
    }

    onActive {
        networkCall(object : Callback<T> {
            override fun onResult(result: Result<T>) {
                when (result) {
                    is Result.Success -> uiState = UiState.Success(data = result.data)
                    is Result.Error -> uiState = UiState.Error(exception = result.exception)
                }
            }

        })
    }
    return uiState
}