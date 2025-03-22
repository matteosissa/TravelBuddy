package dadm.ndescot.quotationshake.data.favourites

import dadm.ndescot.quotationshake.data.favourites.model.toDatabaseDto
import dadm.ndescot.quotationshake.data.favourites.model.toDomain
import dadm.ndescot.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(private val favouritesDataSource: FavouritesDataSource): FavouritesRepository {
    override suspend fun insertQuotation(quotation: Quotation) {
        favouritesDataSource.insertQuotation(quotation.toDatabaseDto())
    }

    override suspend fun deleteQuotation(quotation: Quotation) {
        favouritesDataSource.deleteQuotation(quotation.toDatabaseDto())
    }

    override fun getAllQuotations(): Flow<List<Quotation>> {
        return favouritesDataSource.getAllQuotations().map { dtoList ->
            dtoList.map { dto -> dto.toDomain() }
        }
    }

    override fun getQuotationById(id: String): Flow<Quotation?> {
        return favouritesDataSource.getQuotationById(id).map { dto -> dto?.toDomain() }
    }

    override suspend fun deleteAllQuotations() {
        favouritesDataSource.deleteAllQuotations()
    }
}