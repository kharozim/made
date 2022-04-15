package com.ozimos.core.di

import androidx.room.Room
import com.ozimos.core.data.MovieRepoImpl
import com.ozimos.core.data.local.MovieLocalDataSource
import com.ozimos.core.data.local.room.MovieDb
import com.ozimos.core.data.remote.MovieRemoteDataSource
import com.ozimos.core.data.remote.network.OkHttpClientt
import com.ozimos.core.data.remote.network.RetrofitClient
import com.ozimos.core.data.remote.service.MovieService
import com.ozimos.core.domain.repository.MovieRepo
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit


val databaseModule = module {
    factory { get<MovieDb>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("ozimos".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            MovieDb::class.java, "movie_db.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

    }
}

val remoteModule = module {
    single { provideOkhttpClient() }
    single { provideRetrofitClient(get()) }
    single { provideMovieService(get()) }
}


val repositoryModule = module {
    single { MovieLocalDataSource(get()) }
    single { MovieRemoteDataSource(get()) }
    single<MovieRepo> { MovieRepoImpl(get(), get()) }
}


private fun provideOkhttpClient(): OkHttpClient = OkHttpClientt.getOkhttp()
private fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
    RetrofitClient.getClient(okHttpClient)

private fun provideMovieService(retrofit: Retrofit): MovieService =
    retrofit.create(MovieService::class.java)
