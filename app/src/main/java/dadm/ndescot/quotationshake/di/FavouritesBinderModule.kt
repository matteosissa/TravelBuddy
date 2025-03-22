@file:Suppress(
    "unused"
)

package dadm.ndescot.quotationshake.di

import dadm.ndescot.quotationshake.data.favourites.FavouritesDataSource
import dadm.ndescot.quotationshake.data.favourites.FavouritesDataSourceImpl
import dadm.ndescot.quotationshake.data.favourites.FavouritesRepository
import dadm.ndescot.quotationshake.data.favourites.FavouritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouritesBinderModule {
    @Binds
    abstract fun bindFavouritesDataSource(favouritesDataSource: FavouritesDataSourceImpl): FavouritesDataSource

    @Binds
    abstract fun bindFavouritesRepository(favouritesRepository: FavouritesRepositoryImpl): FavouritesRepository
}