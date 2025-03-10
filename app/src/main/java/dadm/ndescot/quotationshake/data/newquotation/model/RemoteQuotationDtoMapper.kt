package dadm.ndescot.quotationshake.data.newquotation.model

import dadm.ndescot.quotationshake.domain.model.Quotation
import retrofit2.Response
import java.io.IOException

fun RemoteQuotationDto.toDomain() = Quotation(id = quoteLink, quote = quoteText, author = quoteAuthor)

fun Response<RemoteQuotationDto>.toDomain() =
    if (isSuccessful) Result.success((body() as RemoteQuotationDto).toDomain())
    else Result.failure(IOException())