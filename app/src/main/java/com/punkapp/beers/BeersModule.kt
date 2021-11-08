package com.punkapp.beers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BeersModule {

    @Binds
    @Singleton
    abstract fun bindBeerRepository(
        beerRepository: BeerRepositoryImpl
    ): BeerRepository
}