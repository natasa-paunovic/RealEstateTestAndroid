package com.android.realestatete.di

import com.android.realestatete.data.networking.api.ApiService
import com.android.realestatete.data.repository.PropertyRepositoryImpl
import com.android.realestatete.domain.repository.PropertyRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.android.realestatete.BuildConfig
import com.android.realestatete.data.store.PropertyFavouritesDataStore
import com.android.realestatete.domain.preference.PropertyFavouritesPreference
import com.android.realestatete.presentation.viewmodel.PropertyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {


    // Provide PropertyRepository
    single<PropertyRepository> {
        PropertyRepositoryImpl(get())
    }

    // Provide PropertyFavouritesPreference
    single<PropertyFavouritesPreference> {
        PropertyFavouritesDataStore(get())
    }

    // Provide PropertyFavouritesDataStore
    single { PropertyFavouritesDataStore(androidContext()) }


    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    single {
        Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }


}

val viewModelModule = module {
    viewModel { PropertyViewModel(get(), get())
    }

}