package com.lukaszmaly.android.discogsalbums.api

import com.lukaszmaly.android.discogsalbums.ReleaseData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface DiscogsApi {

    @GET(HTTPS_ADDRESS_SPECIFIC_RELEASE)
    fun fetchContents(): Call<ReleaseData>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>

    companion object {
        private const val HTTPS_ADDRESS_SPECIFIC_RELEASE = "/releases/24929597" +
                "?key=HEdkLFcSYnaBIZDloePj&secret=zjXuuQwdhTyiNBWXiIrdvozSGTtcqcmD"
    }
}