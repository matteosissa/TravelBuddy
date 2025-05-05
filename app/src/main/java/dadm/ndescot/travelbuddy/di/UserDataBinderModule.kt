package dadm.ndescot.travelbuddy.di

import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataDataSource
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataDataSourceImpl
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepository
import dadm.ndescot.travelbuddy.data.userdata.local.LocalUserDataRepositoryImpl
import dadm.ndescot.travelbuddy.data.userdata.remote.RemoteUserDataDataSource
import dadm.ndescot.travelbuddy.data.userdata.remote.RemoteUserDataDataSourceImpl
import dadm.ndescot.travelbuddy.data.userdata.remote.RemoteUserDataRepository
import dadm.ndescot.travelbuddy.data.userdata.remote.RemoteUserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module to bind the UserDataRepository and UserDataDataSource implementations.
 * This module is installed in the SingletonComponent, meaning that the bindings will
 * be available for the entire application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataBinderModule {

    /**
     * Binds the LocalUserDataRepositoryImpl to the LocalUserDataRepository interface.
     * This allows for dependency injection of the LocalUserDataRepository implementation
     * wherever the interface is required.
     *
     * @param localUserDataRepositoryImpl The implementation of the LocalUserDataRepository.
     * @return The bound LocalUserDataRepository.
     */
    @Binds
    abstract fun bindLocalUserDataRepository(localUserDataRepositoryImpl: LocalUserDataRepositoryImpl): LocalUserDataRepository

    /**
     * Binds the LocalUserDataDataSourceImpl to the LocalUserDataDataSource interface.
     * This allows for dependency injection of the LocalUserDataDataSource implementation
     * wherever the interface is required.
     *
     * @param localUserDataDataSourceImpl The implementation of the LocalUserDataDataSource.
     * @return The bound LocalUserDataDataSource.
     */
    @Binds
    abstract fun bindLocalUserDataDataSource(localUserDataDataSourceImpl: LocalUserDataDataSourceImpl): LocalUserDataDataSource

    /**
     * Binds the RemoteUserDataRepositoryImpl to the RemoteUserDataRepository interface.
     * This allows for dependency injection of the RemoteUserDataRepository implementation
     * wherever the interface is required.
     *
     * @param remoteUserDataRepositoryImpl The implementation of the RemoteUserDataRepository.
     * @return The bound RemoteUserDataRepository.
     */
    @Binds
    abstract fun bindRemoteUserDataRepository(remoteUserDataRepositoryImpl: RemoteUserDataRepositoryImpl): RemoteUserDataRepository

    /**
     * Binds the RemoteUserDataDataSourceImpl to the RemoteUserDataDataSource interface.
     * This allows for dependency injection of the RemoteUserDataDataSource implementation
     * wherever the interface is required.
     *
     * @param remoteUserDataDataSourceImpl The implementation of the RemoteUserDataDataSource.
     * @return The bound RemoteUserDataDataSource.
     */
    @Binds
    abstract fun bindRemoteUserDataDataSource(remoteUserDataDataSourceImpl: RemoteUserDataDataSourceImpl): RemoteUserDataDataSource
}