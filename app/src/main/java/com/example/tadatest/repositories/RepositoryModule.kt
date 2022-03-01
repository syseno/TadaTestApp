package com.example.tadatest.repositories

import com.example.tadatest.networks.Api
import com.example.tadatest.networks.ApiService
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository(get()) }
}

fun createRepository(
    api: ApiService
) : PhotosRepository = PhotosRepository(api)