package com.example.jetpackcomposepoc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val navMutableLiveData: MutableLiveData<NavData> = MutableLiveData()
    fun getDataValue(): LiveData<NavData> {
        return navMutableLiveData
    }

    fun goToCardsScreen() {
        navMutableLiveData.value = NavData(state = NavState.GO_TO_CARDS_SCREEN)
    }

    data class NavData(
        val state: NavState,
    )

    enum class NavState {
        GO_TO_CARDS_SCREEN
    }
}
