package dadm.ndescot.quotationshake.ui.newquotation

import dadm.ndescot.quotationshake.domain.model.Quotation

interface NewQuotationRepository {
    suspend fun getNewQuotation(): Result<Quotation>
}