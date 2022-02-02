package com.example.desafioaula2ioasys.di

import com.example.desafioaula2ioasys.data_remote.datasource.BooksDatasourceImpl
import com.example.desafioaula2ioasys.data_remote.datasource.LoginDatasourceImpl
import com.example.desafioaula2ioasys.data_remote.service.AuthService
import com.example.desafioaula2ioasys.data_remote.service.BookService
import com.example.desafioaula2ioasys.data_remote.utils.ApiConstants.BASE_URL
import com.example.desafioaula2ioasys.data_remote.utils.WebServiceFactory
import com.example.desafioaula2ioasys.data.datasource.remote.BooksRemoteDatasource
import com.example.desafioaula2ioasys.data.datasource.remote.LoginRemoteDatasource
import org.koin.dsl.module

val dataRemoteModule = module {
    single<LoginRemoteDatasource>{
        LoginDatasourceImpl(get())
    }
    single<BooksRemoteDatasource> {
        BooksDatasourceImpl(get())
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