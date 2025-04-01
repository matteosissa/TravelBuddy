package dadm.ndescot.travelbuddy.data.newquotation

import dadm.ndescot.travelbuddy.data.newquotation.model.toDomain
import dadm.ndescot.travelbuddy.data.settings.SettingsRepository
import dadm.ndescot.travelbuddy.domain.model.Quotation
import dadm.ndescot.travelbuddy.utils.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val newQuotationDataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker,
    private val settingsRepository: SettingsRepository
): NewQuotationRepository {
    private lateinit var language: String
    init {
        CoroutineScope(SupervisorJob()).launch {
            settingsRepository.getLanguage().collect { languageCode ->
                language = languageCode.ifEmpty{ "en" }
            }
        }
    }

    override suspend fun getNewQuotation(): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) {
             newQuotationDataSource.getQuotation(language).toDomain()
        } else {
             Result.failure(NoInternetException())
        }
    }
}