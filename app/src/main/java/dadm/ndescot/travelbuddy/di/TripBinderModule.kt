package dadm.ndescot.travelbuddy.di

import dadm.ndescot.travelbuddy.data.trip.TripDataSource
import dadm.ndescot.travelbuddy.data.trip.TripDataSourceImpl
import dadm.ndescot.travelbuddy.data.trip.TripRepository
import dadm.ndescot.travelbuddy.data.trip.TripRepositoryImpl
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