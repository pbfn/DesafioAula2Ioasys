package com.example.desafioaula2ioasys.di

import com.example.desafioaula2ioasys.data.data_remote.datasource.BooksDatasourceImpl
import com.example.desafioaula2ioasys.data.data_remote.datasource.LoginDatasourceImpl
import com.example.desafioaula2ioasys.data.datasource.BooksDatasource
import com.example.desafioaula2ioasys.data.datasource.LoginDatasource
import org.koin.dsl.module

val dataRemoteModule = module {
    single<LoginDatasource>{
        LoginDatasourceImpl()
    }
    single<BooksDatasource> {
        BooksDatasourceImpl()
    }

}