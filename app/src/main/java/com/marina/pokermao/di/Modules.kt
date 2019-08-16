package com.marina.pokermao.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.marina.pokermao.api.Interceptor.AuthInterceptor
import com.marina.pokermao.api.PokemonService
import com.marina.pokermao.repository.PokemonRepository
import com.marina.pokermao.repository.PokemonRepositotyImpl
import com.marina.pokermao.view.form.FormPokemonViewModel
import com.marina.pokermao.view.list.ListPokemonsViewModel
import com.marina.pokermao.view.splash.SplashViewModel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClientAuth(get()) }
    single { createNetworkClient(get()).create(PokemonService::class.java) }
    single { createPicassoAuth(get(), get()) }
}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositotyImpl(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ListPokemonsViewModel(get()) }
    viewModel { FormPokemonViewModel(get()) }
}

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pokedexdx.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}

private fun createPicassoAuth(context: Context, okHttpClient: OkHttpClient): Picasso {
    return Picasso.Builder(context).downloader(OkHttp3Downloader(okHttpClient)).build()
}