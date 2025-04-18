package dadm.ndescot.travelbuddy.di

import dadm.ndescot.travelbuddy.data.guide.GuideDataSource
import dadm.ndescot.travelbuddy.data.guide.GuideDataSourceImpl
import dadm.ndescot.travelbuddy.data.guide.GuideRepository
import dadm.ndescot.travelbuddy.data.guide.GuideRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GuideBinderModule {
    @Binds
    abstract fun bindGuideDataSource(datasource: GuideDataSourceImpl): GuideDataSource

    @Binds
    abstract fun bindGuideRepository(repository: GuideRepositoryImpl): GuideRepository

}