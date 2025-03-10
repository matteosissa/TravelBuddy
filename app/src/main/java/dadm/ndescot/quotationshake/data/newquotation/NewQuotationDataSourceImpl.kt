package dadm.ndescot.quotationshake.data.newquotation

import dadm.ndescot.quotationshake.data.newquotation.model.RemoteQuotationDto
import dadm.ndescot.quotationshake.data.newquotation.model.toDomain
import dadm.ndescot.quotationshake.domain.model.Quotation
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

interface NewQuotationRetrofit {
    @GET("api/1.0/?method=getQuote&format=json&lang=en")
    suspend fun getQuotation(): Response<RemoteQuotationDto>
}

class NewQuotationDataSourceImpl @Inject constructor(retrofit: Retrofit)
    : NewQuotationDataSource {

    private val retrofitQuotationService =
        retrofit.create(NewQuotationRetrofit::class.java)

    override suspend fun getQuotation(): Response<RemoteQuotationDto> {
        return try {
            retrofitQuotationService.getQuotation()
        } catch (e: Exception) {
            Response.error(
                400, // Could be any other code and text, because we are not using it
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }
}