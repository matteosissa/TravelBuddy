package dadm.ndescot.quotationshake.data.newquotation

import dadm.ndescot.quotationshake.data.newquotation.model.toDomain
import dadm.ndescot.quotationshake.domain.model.Quotation
import dadm.ndescot.quotationshake.utils.NoInternetException
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val newQuotationDataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker
): NewQuotationRepository {

    override suspend fun getNewQuotation(): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) {
             newQuotationDataSource.getQuotation().toDomain()
        } else {
             Result.failure(NoInternetException())
        }
    }
}