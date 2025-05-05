package dadm.ndescot.travelbuddy.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module to provide the [ConnectivityManager] instance.
 *
 * This module is installed in the [SingletonComponent], which means that the provided
 * dependencies will have a singleton scope.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the [ConnectivityManager] instance.
     *
     * @param context The application context.
     * @return The [ConnectivityManager] instance.
     */
    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}