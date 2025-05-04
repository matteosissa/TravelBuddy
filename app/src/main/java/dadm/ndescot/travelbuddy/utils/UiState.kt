package dadm.ndescot.travelbuddy.utils

sealed class UiState {
    data object Idle : UiState()
    data class Success(val data: String) : UiState()
    data class Error(val message: String) : UiState()
}