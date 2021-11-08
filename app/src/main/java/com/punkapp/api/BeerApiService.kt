package com.punkapp.api

import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface BeerApiService {

    @GET("v2/beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<ApiBeer>
}