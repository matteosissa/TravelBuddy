package dadm.ndescot.travelbuddy.utils

/**
 * This file contains the UiState sealed class used to represent the different states of the UI.
 * It is used in conjunction with the ViewModel to manage the state of the UI.
 * The UiState sealed class has three states: Idle, Success, and Error.
 */
sealed class UiState {
    data object Idle : UiState()
    data class Success(val data: String) : UiState()
    data class Error(val message: String) : UiState()
}