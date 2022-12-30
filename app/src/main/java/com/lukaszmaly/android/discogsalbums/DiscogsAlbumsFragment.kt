package com.lukaszmaly.android.discogsalbums

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DiscogsAlbumsFragment: Fragment() {

    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val discogsLiveData: LiveData<ReleaseData> = DiscogsFetch().fetchContents()
        discogsLiveData.observe(
            this,
            Observer { ReleaseData ->
                Log.d(TAG, "Response received: $ReleaseData")
            })
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
        private const val TAG = "DiscogsAlbumsFragment"
    }
}