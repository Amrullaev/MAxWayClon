package com.amrullaev.maxwayclon.di

import androidx.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.online_shopping.navigation.NavigationDispatcher
import uz.gita.online_shopping.navigation.NavigationHandler
import uz.gita.online_shopping.navigation.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun navigator(dispatcher: NavigationDispatcher): Navigator

    @Binds
    fun navigatorHandler(dispatcher: NavigationDispatcher): NavigationHandler
}