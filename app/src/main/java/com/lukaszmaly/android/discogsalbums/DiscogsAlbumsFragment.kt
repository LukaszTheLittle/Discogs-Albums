package com.lukaszmaly.android.discogsalbums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukaszmaly.android.discogsalbums.api.DiscogsApi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class DiscogsAlbumsFragment: Fragment() {

    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.discogs.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val discogsApi: DiscogsApi = retrofit.create(DiscogsApi::class.java)

        val discogsApiPageRequest: Call<String> = discogsApi.fetchContents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_discogs_albums, container, false)

        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return view
    }

    companion object {
        fun newInstance() = DiscogsAlbumsFragment()
    }
}