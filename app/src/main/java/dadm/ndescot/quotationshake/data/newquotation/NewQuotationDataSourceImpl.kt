package dadm.ndescot.quotationshake.data.newquotation

import dadm.ndescot.quotationshake.domain.model.Quotation
import javax.inject.Inject

class NewQuotationDataSourceImpl @Inject constructor() : NewQuotationDataSource {
    override suspend fun getQuotation(): Result<Quotation> {
        val num = (0..9).random()
        if (num != 0){
            return Result.success((Quotation(
                id = "$num",
                quote = "Quotation text #$num",
                author = "Author #$num"
            )))
        } else {
            return Result.failure(RuntimeException("Failed to retrieve quote"))
        }
    }
}