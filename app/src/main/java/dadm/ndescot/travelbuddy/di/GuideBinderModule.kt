package dadm.ndescot.travelbuddy.di

import dadm.ndescot.travelbuddy.data.guide.GuideDataSource
import dadm.ndescot.travelbuddy.data.guide.GuideDataSourceImpl
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.guide.GuideRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Single binder module that contains the bindings to manage access to guide data (both locally and remotely)
 *
 * @see [UserDataBinderModule]
 * @see [TripBinderModule]
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class GuideBinderModule {
    /**
     * Binds the [GuideDataSourceImpl] to the [GuideDataSource] interface.
     * This allows for dependency injection of the data source implementation.
     *
     * @param datasource The implementation of the data source to be bound.
     * @return The bound data source interface.
     */
    @Binds
    abstract fun bindGuideDataSource(datasource: GuideDataSourceImpl): GuideDataSource

    /**
     * Binds the [GuideRepositoryImpl] to the [GuideRepository] interface.
     * This allows for dependency injection of the repository implementation.
     *
     * @param repository The implementation of the repository to be bound.
     * @return The bound repository interface.
     */
    @Binds
    abstract fun bindGuideRepository(repository: GuideRepositoryImpl): GuideRepository
}