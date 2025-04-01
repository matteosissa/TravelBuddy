package dadm.ndescot.travelbuddy.data.favourites

import androidx.room.Database
import androidx.room.RoomDatabase
import dadm.ndescot.travelbuddy.data.favourites.model.DatabaseQuotationDto

@Database(entities = [DatabaseQuotationDto::class], version = 1)
abstract class FavouritesDatabase: RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}