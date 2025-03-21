package dadm.ndescot.quotationshake.di

import dadm.ndescot.quotationshake.data.settings.SettingsDataSource
import dadm.ndescot.quotationshake.data.settings.SettingsDataSourceImpl
import dadm.ndescot.quotationshake.data.settings.SettingsRepository
import dadm.ndescot.quotationshake.data.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {
    @Binds
    abstract fun bindSettingsDataSource(dataSource: SettingsDataSourceImpl): SettingsDataSource
    @Binds
    abstract fun bindSettingsRepository(repository: SettingsRepositoryImpl): SettingsRepository

}