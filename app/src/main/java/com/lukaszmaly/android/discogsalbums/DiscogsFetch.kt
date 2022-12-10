package com.lukaszmaly.android.discogsalbums

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lukaszmaly.android.discogsalbums.api.DiscogsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun fetchContents(): LiveData<String> {
        val responseLivedata: MutableLiveData<String> = MutableLiveData()
        val discogsRequest: Call<String> = discogsApi.fetchContents()

        discogsRequest.enqueue(object: Callback<String> {

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data", t)
            }

            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                Log.d(TAG, "Response received")
                responseLivedata.value = response.body()
            }
        })
        return responseLivedata
    }

    companion object {
        private const val TAG = "DiscogsFetch"
    }
}