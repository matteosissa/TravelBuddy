package dadm.ndescot.quotationshake.data.favourites

import dadm.ndescot.quotationshake.data.favourites.model.DatabaseQuotationDto
import kotlinx.coroutines.flow.Flow

interface FavouritesDataSource {
    suspend fun insertQuotation(quotation: DatabaseQuotationDto)
    suspend fun deleteQuotation(quotation: DatabaseQuotationDto)
    fun getAllQuotations(): Flow<List<DatabaseQuotationDto>>
    fun getQuotationById(id: String): Flow<DatabaseQuotationDto?>
    suspend fun deleteAllQuotations()
}