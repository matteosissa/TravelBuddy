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
 * Single binder module that contains the bindings to manage access to user data (both locally and remotely)
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataBinderModule {

    @Binds
    abstract fun bindLocalUserDataRepository(localUserDataRepositoryImpl: LocalUserDataRepositoryImpl): LocalUserDataRepository

    @Binds
    abstract fun bindLocalUserDataDataSource(localUserDataDataSourceImpl: LocalUserDataDataSourceImpl): LocalUserDataDataSource

    @Binds
    abstract fun bindRemoteUserDataRepository(remoteUserDataRepositoryImpl: RemoteUserDataRepositoryImpl): RemoteUserDataRepository

    @Binds
    abstract fun bindRemoteUserDataDataSource(remoteUserDataDataSourceImpl: RemoteUserDataDataSourceImpl): RemoteUserDataDataSource


}