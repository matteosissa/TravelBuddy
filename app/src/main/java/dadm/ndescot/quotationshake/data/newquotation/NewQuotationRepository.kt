package dadm.ndescot.quotationshake.data.newquotation

import dadm.ndescot.quotationshake.domain.model.Quotation

interface NewQuotationRepository {
    suspend fun getNewQuotation(): Result<Quotation>
}