package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.framwork.di

import android.content.Context
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.repository.SearchDeliveriesRepositoryImpl
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.repository.ShipmentCategoryRepositoryImpl
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.repository.ShipmentHistoryRepositoryImpl
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.SearchDeliveriesRepository
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.ShipmentCategoryRepository
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.domain.repository.ShipmentHistoryRepository
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.framwork.util.JsonDecoder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesJsonDecoder(
        @ApplicationContext appContext: Context,
        moshi: Moshi
    ): JsonDecoder {
        return JsonDecoder(appContext, moshi)
    }

    @Singleton
    @Provides
    fun provideSearchDeliveriesRepository(jsonDecoder: JsonDecoder) =
        SearchDeliveriesRepositoryImpl(jsonDecoder) as SearchDeliveriesRepository

    @Singleton
    @Provides
    fun provideShipmentCategoryRepository(jsonDecoder: JsonDecoder) =
        ShipmentCategoryRepositoryImpl(jsonDecoder) as ShipmentCategoryRepository

    @Singleton
    @Provides
    fun provideShipmentHistoryRepository(jsonDecoder: JsonDecoder) =
        ShipmentHistoryRepositoryImpl(jsonDecoder) as ShipmentHistoryRepository

}
