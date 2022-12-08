package com.lukaszmaly.android.discogsalbums.api

import retrofit2.Call
import retrofit2.http.GET

interface DiscogsApi {

    @GET("/")
    fun fetchContents(): Call<String>
}