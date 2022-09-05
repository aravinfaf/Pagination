package com.aravind.pagination

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("character")
    suspend fun getDataFromAPI(@Query("page") query : Int  ) : RickyAndMortyList

}