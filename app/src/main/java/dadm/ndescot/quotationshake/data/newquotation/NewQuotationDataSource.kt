package dadm.ndescot.quotationshake.data.newquotation

import dadm.ndescot.quotationshake.data.newquotation.model.RemoteQuotationDto
import retrofit2.Response

interface NewQuotationDataSource {
    suspend fun getQuotation(lang: String):Response<RemoteQuotationDto>
}