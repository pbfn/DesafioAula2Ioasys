package com.example.desafioaula2ioasys.di

import com.example.desafioaula2ioasys.data_remote.datasource.BooksDataSourceImpl
import com.example.desafioaula2ioasys.data_remote.datasource.LoginDataSourceImpl
import com.example.desafioaula2ioasys.data_remote.service.AuthService
import com.example.desafioaula2ioasys.data_remote.service.BookService
import com.example.desafioaula2ioasys.data_remote.utils.ApiConstants.BASE_URL
import com.example.desafioaula2ioasys.data_remote.utils.WebServiceFactory
import com.example.desafioaula2ioasys.data.datasource.remote.BooksRemoteDataSource
import com.example.desafioaula2ioasys.data.datasource.remote.LoginRemoteDataSource
import org.koin.dsl.module

val dataRemoteModule = module {
    single<LoginRemoteDataSource>{
        LoginDataSourceImpl(get())
    }
    single<BooksRemoteDataSource> {
        BooksDataSourceImpl(get())
    }
    single<AuthService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL)
    }

    single<BookService> {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL)
    }

    single { WebServiceFactory.providerOkHttClient() }

}