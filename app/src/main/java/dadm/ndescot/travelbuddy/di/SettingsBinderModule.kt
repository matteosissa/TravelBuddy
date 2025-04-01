package dadm.ndescot.travelbuddy.di

import dadm.ndescot.travelbuddy.data.settings.SettingsDataSource
import dadm.ndescot.travelbuddy.data.settings.SettingsDataSourceImpl
import dadm.ndescot.travelbuddy.data.settings.SettingsRepository
import dadm.ndescot.travelbuddy.data.settings.SettingsRepositoryImpl
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