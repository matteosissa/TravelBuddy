package dadm.ndescot.quotationshake.ui.newquotation

import dadm.ndescot.quotationshake.domain.model.Quotation
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(): NewQuotationRepository {
    override suspend fun getNewQuotation(): Result<Quotation> {
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