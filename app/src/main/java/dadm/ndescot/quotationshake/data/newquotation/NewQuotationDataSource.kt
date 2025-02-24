package dadm.ndescot.quotationshake.data.newquotation

import dadm.ndescot.quotationshake.domain.model.Quotation

interface NewQuotationDataSource {
    suspend fun getQuotation():Result<Quotation>
}