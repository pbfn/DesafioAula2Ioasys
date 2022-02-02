package com.example.desafioaula2ioasys.di

import com.example.desafioaula2ioasys.data.datasource.local.BooksLocalDataSource
import com.example.desafioaula2ioasys.data.datasource.local.LoginLocalDataSource
import com.example.desafioaula2ioasys.data_local.datasource.BooksLocalDataSourceImpl
import com.example.desafioaula2ioasys.data_local.datasource.LoginLocalDataSourceImpl
import com.example.desafioaula2ioasys.data_local.utils.SharedPreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {
    single { SharedPreferencesHelper(androidContext()) }
    single<LoginLocalDataSource> { LoginLocalDataSourceImpl(get()) }
    single<BooksLocalDataSource> { BooksLocalDataSourceImpl(get()) }
}