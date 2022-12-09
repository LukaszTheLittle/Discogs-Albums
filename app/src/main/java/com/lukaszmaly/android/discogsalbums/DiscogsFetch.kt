package com.lukaszmaly.android.discogsalbums

import com.lukaszmaly.android.discogsalbums.api.DiscogsApi
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class DiscogsFetch {

    private val discogsApi: DiscogsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.discogs.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        discogsApi = retrofit.create(DiscogsApi::class.java)
    }

    companion object {
        private const val TAG = "DiscogsFetch"
    }
}