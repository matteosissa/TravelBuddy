package dadm.ndescot.quotationshake.ui.newquotation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update

class NewQuotationViewModel: ViewModel() {
    private val generatedName = getUserName()
    private val _userName = MutableStateFlow<String>(generatedName)

    val userName = _userName.asStateFlow()

    private fun getUserName() : String {

        return setOf("Alice", "Bob", "Charlie", "David", "Emma", "Nicolas", "").random()

    }
}