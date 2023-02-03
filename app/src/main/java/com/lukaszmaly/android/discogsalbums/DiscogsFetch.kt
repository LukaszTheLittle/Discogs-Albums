package com.lukaszmaly.android.discogsalbums

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lukaszmaly.android.discogsalbums.api.DiscogsApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscogsFetch {

    private val discogsApi: DiscogsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.discogs.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        discogsApi = retrofit.create(DiscogsApi::class.java)
    }

    fun fetchContents(): LiveData<ReleaseData> {
        val responseLivedata: MutableLiveData<ReleaseData> = MutableLiveData()
        val discogsRequest: Call<ReleaseData> = discogsApi.fetchContents()

        discogsRequest.enqueue(object: Callback<ReleaseData> {

            override fun onFailure(call: Call<ReleaseData>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data", t)
            }

            override fun onResponse(
                call: Call<ReleaseData>,
                response: Response<ReleaseData>
            ) {
                Log.d(TAG, "Response received")
                val discogsResponse: ReleaseData? = response.body()
                responseLivedata.value = discogsResponse
            }
        })
        return responseLivedata
    }

    @WorkerThread
    fun fetchThumbnail(url: String): Bitmap? {
        val response: Response<ResponseBody> = discogsApi.fetchUrlBytes(url).execute()
        val bitmap = response.body()?.byteStream()?.use(BitmapFactory::decodeStream)
        Log.i(TAG, "Decoded bitmap=$bitmap from Response=$response")
        return bitmap
    }

    companion object {
        private const val TAG = "DiscogsFetch"
    }
}