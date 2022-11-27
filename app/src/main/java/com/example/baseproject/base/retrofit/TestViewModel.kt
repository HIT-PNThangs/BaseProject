package com.example.baseproject.base.retrofit

import androidx.lifecycle.ViewModel
import com.example.baseproject.base.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestViewModel : ViewModel() {
    private val apiService by lazy {
        RetrofitClient.getInstance().create(ApiService::class.java)
    }

    fun getData(
        onSuccess: ((result: List<User>) -> Unit)? = null,
        onFailed: ((message: String) -> Unit)? = null,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            flow {
                emit(apiService.getUsers())
            }.flowOn(Dispatchers.IO)
                .catch { e ->
                    withContext(Dispatchers.Main) {
                        onFailed?.invoke(e.message ?: "")
                    }
                }
                .collect {
                    withContext(Dispatchers.Main) {
                        onSuccess?.invoke(it)
                    }
                }
        }
    }

}

fun <T> Flow<T>.subscrible() {

}