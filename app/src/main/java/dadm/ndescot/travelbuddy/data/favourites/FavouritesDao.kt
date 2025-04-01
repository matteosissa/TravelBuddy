package dadm.ndescot.travelbuddy.data.favourites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dadm.ndescot.travelbuddy.data.favourites.model.DatabaseQuotationDto
import kotlinx.coroutines.flow.Flow
import dadm.ndescot.travelbuddy.data.favourites.FavouritesContract.FavouritesTable


@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotation(quotation: DatabaseQuotationDto)

    @Delete
    suspend fun deleteQuotation(quotation: DatabaseQuotationDto)

    @Query("SELECT * FROM ${FavouritesTable.TABLE_NAME}")
    fun getAllQuotations(): Flow<List<DatabaseQuotationDto>>

    @Query("SELECT * FROM ${FavouritesTable.TABLE_NAME} WHERE ${FavouritesTable.COL_ID} = :id")
    fun getQuotationById(id: String): Flow<DatabaseQuotationDto?>

    @Query("DELETE FROM ${FavouritesTable.TABLE_NAME}")
    suspend fun deleteAllQuotations()
}