package dadm.ndescot.travelbuddy.data.favourites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.ndescot.travelbuddy.data.favourites.FavouritesContract.FavouritesTable


@Entity(tableName = FavouritesTable.TABLE_NAME)
data class DatabaseQuotationDto(
    @PrimaryKey
    @ColumnInfo(name = FavouritesTable.COL_ID)
    val id: String,

    @ColumnInfo(name = FavouritesTable.COL_TEXT)
    val text: String,

    @ColumnInfo(name = FavouritesTable.COL_AUTHOR)
    val author: String
)
