package com.plandel.compose002.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.plandel.compose002.api.RetrofitService
import com.plandel.compose002.repository.MainRepository
import com.plandel.compose002.ui.MainViewModel
import com.plandel.compose002.ui.SearchViewModel
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
}


val repositoryModule = module {
    single {
        MainRepository(get())
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
