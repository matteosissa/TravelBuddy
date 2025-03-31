package dadm.ndescot.quotationshake.di

import dadm.ndescot.quotationshake.data.trip.TripDataSource
import dadm.ndescot.quotationshake.data.trip.TripDataSourceImpl
import dadm.ndescot.quotationshake.data.trip.TripRepository
import dadm.ndescot.quotationshake.data.trip.TripRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TripBinderModule {
    @Binds
    abstract fun bindTripDataSource(dataSource: TripDataSourceImpl): TripDataSource
    @Binds
    abstract fun bindTripRepository(repository: TripRepositoryImpl): TripRepository
}