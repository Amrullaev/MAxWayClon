package com.amrullaev.maxwayclon.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.amrullaev.maxwayclon.repository.MaxWayRepository
import com.amrullaev.maxwayclon.repository.impl.MaxWayRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindMaxWayRepository(impl: MaxWayRepositoryImpl): MaxWayRepository

}