package dadm.ndescot.quotationshake.ui.newquotation

import dadm.ndescot.quotationshake.data.newquotation.ConnectivityChecker
import dadm.ndescot.quotationshake.data.newquotation.NewQuotationDataSource
import dadm.ndescot.quotationshake.domain.model.Quotation
import dadm.ndescot.quotationshake.utils.NoInternetException
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val newQuotationDataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker
): NewQuotationRepository {

    override suspend fun getNewQuotation(): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) {
             newQuotationDataSource.getQuotation()
        } else {
             Result.failure(NoInternetException())
        }
    }
}