package com.buchi.fullentry.movie.di

import android.content.Context
import androidx.room.Room
import com.buchi.fullentry.BuildConfig
import com.buchi.fullentry.movie.data.MovieRepository
import com.buchi.fullentry.movie.data.MovieService
import com.buchi.fullentry.movie.data.MoviesRepositoryImpl
import com.buchi.fullentry.movie.data.cache.MovieDao
import com.buchi.fullentry.movie.data.cache.MoviesAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)    // Movies Data is only needed when Movie activity is alive.
object MovieModule {

    @Provides
    fun provideMovieService(): MovieService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_BASE_URL)
            .build()
            .create(MovieService::class.java)
    }

    @Provides
    fun provideMovieDb(@ApplicationContext applicationContext: Context): MoviesAppDatabase {
        return Room.databaseBuilder(applicationContext, MoviesAppDatabase::class.java, "movies-db").build()
    }

    @Provides
    fun provideMovieDao(db: MoviesAppDatabase): MovieDao {
        return db.moviesDao()
    }

    @Provides
    fun provideMovieRepository(@ApplicationContext context: Context, network: MovieService, cache: MovieDao): MovieRepository {
        return MoviesRepositoryImpl(context, network, cache)
    }
}