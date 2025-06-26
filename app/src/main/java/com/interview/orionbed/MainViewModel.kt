package com.interview.orionbed

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.orionbed.network.model.InitResponse
import com.interview.orionbed.usecase.LoadInitialDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadInitialDataUseCase: LoadInitialDataUseCase
) : ViewModel() {

    private val _initData = mutableStateOf<InitResponse?>(null)
    val initData: State<InitResponse?> = _initData

    init {
        viewModelScope.launch {
            try {
                val data = loadInitialDataUseCase()
                _initData.value = data
                Log.i("zebra", "Init data loaded: $data")
            } catch (e: Exception) {
                Log.e("zebra", "API call failed", e)
            }
        }
    }
}