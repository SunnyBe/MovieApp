package com.buchi.fullentry.cars.di

import android.content.Context
import androidx.room.Room
import com.buchi.fullentry.BuildConfig
import com.buchi.fullentry.cars.data.CarRepository
import com.buchi.fullentry.cars.data.CarRepositoryImpl
import com.buchi.fullentry.cars.data.cache.CarAppDatabase
import com.buchi.fullentry.cars.data.cache.CarDao
import com.buchi.fullentry.cars.data.network.CarService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarsModule {

    @Provides
    @Singleton
    fun provideCarService(): CarService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(addClient())
            .build()
            .create(CarService::class.java)
    }

    @Provides
    @Singleton
    fun provideCarDb(@ApplicationContext applicationContext: Context): CarAppDatabase {
        return Room.databaseBuilder(applicationContext, CarAppDatabase::class.java, "cars-db")
            .build()
    }

    @Provides
    fun provideCarDao(db: CarAppDatabase): CarDao {
        return db.carsDao()
    }

    @Provides
    fun provideCarRepository(
        @ApplicationContext context: Context,
        network: CarService,
        cache: CarAppDatabase
    ): CarRepository {
        return CarRepositoryImpl(context, network, cache)
    }

    private fun addClient(): OkHttpClient {
        val headerInterceptor = Interceptor { chain ->
            chain.run {
                proceed(
                    request()
                        .newBuilder()
                        .build()
                )
            }
        }

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return okHttpClient.build()
    }
}