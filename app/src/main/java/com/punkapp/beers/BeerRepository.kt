package com.punkapp.beers

import com.punkapp.api.BeerApiService
import javax.inject.Inject
import javax.inject.Singleton

private const val PER_PAGE = 30

interface BeerRepository {
    suspend fun getBeers(): Result<List<Beer>>
}

@Singleton
class BeerRepositoryImpl @Inject constructor(val beersApiService: BeerApiService) : BeerRepository {

    private var page = 1

    override suspend fun getBeers(): Result<List<Beer>> = Result.runCatching {
        beersApiService.getBeers(page, PER_PAGE).map {
            it.toBeer()
        }
    }

}