package dadm.ndescot.quotationshake.di

import dadm.ndescot.quotationshake.ui.newquotation.NewQuotationRepository
import dadm.ndescot.quotationshake.ui.newquotation.NewQuotationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NewQuotationBinderModule {

    @Binds
    abstract fun bindNewQuotationRepository(
        newQuotationRepositoryImpl: NewQuotationRepositoryImpl
    ): NewQuotationRepository
}