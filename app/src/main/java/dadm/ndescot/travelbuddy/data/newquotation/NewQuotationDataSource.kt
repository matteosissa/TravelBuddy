package dadm.ndescot.travelbuddy.data.newquotation

import dadm.ndescot.travelbuddy.data.newquotation.model.RemoteQuotationDto
import retrofit2.Response

interface NewQuotationDataSource {
    suspend fun getQuotation(lang: String):Response<RemoteQuotationDto>
}