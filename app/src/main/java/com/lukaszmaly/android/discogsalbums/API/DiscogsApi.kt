package com.lukaszmaly.android.discogsalbums.API

import retrofit2.Call
import retrofit2.http.GET

interface DiscogsApi {

    @GET("/")
    fun fetchContents(): Call<String>
}