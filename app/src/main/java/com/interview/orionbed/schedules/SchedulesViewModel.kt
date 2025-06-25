package com.interview.orionbed.schedules

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SchedulesViewModel : ViewModel() {

    // Controls whether the sheet should be visible
    var isBottomSheetVisible = mutableStateOf(false)
        private set

    fun showBottomSheet() {
        isBottomSheetVisible.value = true
    }

    // Call this ONLY after sheetState.hide() animation completes
    fun hideBottomSheet() {
        isBottomSheetVisible.value = false
    }
}

