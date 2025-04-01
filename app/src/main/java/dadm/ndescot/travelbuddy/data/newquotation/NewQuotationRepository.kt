package dadm.ndescot.travelbuddy.data.newquotation

import dadm.ndescot.travelbuddy.domain.model.Quotation

interface NewQuotationRepository {
    suspend fun getNewQuotation(): Result<Quotation>
}