package com.plandel.compose002.di

import android.app.Application
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.plandel.compose002.api.RetrofitService
import com.plandel.compose002.data.CardDao
import com.plandel.compose002.data.CardDatabase
import com.plandel.compose002.repository.MainRepository
import com.plandel.compose002.ui.aboutCard.AboutCardViewModel
import com.plandel.compose002.ui.main.MainViewModel
import com.plandel.compose002.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        AboutCardViewModel(get())
    }
}


val repositoryModule = module {
    single {
        MainRepository(get(),get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    single { provideUseApi(get()) }
    single { provideGson() }
}

val retrofitModule = module {
    fun provideRetrofit(factory: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://db.ygoprodeck.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .build()
    }

    single { provideRetrofit(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): CardDatabase {
        return Room.databaseBuilder(application, CardDatabase::class.java,"card.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: CardDatabase): CardDao {
        return database.cardDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}
