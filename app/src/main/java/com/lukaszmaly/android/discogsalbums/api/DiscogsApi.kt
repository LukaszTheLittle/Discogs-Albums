package com.lukaszmaly.android.discogsalbums.api

import com.lukaszmaly.android.discogsalbums.ReleaseData
import retrofit2.Call
import retrofit2.http.GET

interface DiscogsApi {

    @GET(HTTPS_ADDRESS_SPECIFIC_RELEASE)
    fun fetchContents(): Call<ReleaseData>

    companion object {
        private const val HTTPS_ADDRESS_SPECIFIC_RELEASE = "/releases/24929597" +
                "?key=HEdkLFcSYnaBIZDloePj&secret=zjXuuQwdhTyiNBWXiIrdvozSGTtcqcmD"
    }
}