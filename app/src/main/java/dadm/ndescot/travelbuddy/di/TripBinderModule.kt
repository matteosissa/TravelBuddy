package dadm.ndescot.travelbuddy.di

import dadm.ndescot.travelbuddy.data.trip.TripDataSource
import dadm.ndescot.travelbuddy.data.trip.TripDataSourceImpl
import dadm.ndescot.travelbuddy.data.trip.TripRepository
import dadm.ndescot.travelbuddy.data.trip.TripRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Single binder module that contains the bindings to manage access to trip data (both locally and remotely)
 *
 * @see [UserDataBinderModule]
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TripBinderModule {

    /**
     * Binds the [TripRepositoryImpl] to the [TripRepository] interface.
     */
    @Binds
    abstract fun bindTripDataSource(dataSource: TripDataSourceImpl): TripDataSource

    /**
     * Binds the [TripRepositoryImpl] to the [TripRepository] interface.
     */
    @Binds
    abstract fun bindTripRepository(repository: TripRepositoryImpl): TripRepository
}