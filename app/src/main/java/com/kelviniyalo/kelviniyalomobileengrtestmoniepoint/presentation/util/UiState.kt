package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
}