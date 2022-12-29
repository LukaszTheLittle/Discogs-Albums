package com.lukaszmaly.android.discogsalbums

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DiscogsAlbumsFragment: Fragment() {

    private lateinit var discogsAlbumsViewModel: DiscogsAlbumsViewModel
    private lateinit var photoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        discogsAlbumsViewModel =
            ViewModelProvider(this).get(DiscogsAlbumsViewModel::class.java)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discogsAlbumsViewModel.releaseDataLiveData.observe(
            viewLifecycleOwner,
            Observer { releaseData ->
                Log.d(TAG, "Have release data from ViewModel $releaseData")
            }
        )
    }

    companion object {
        fun newInstance() = DiscogsAlbumsFragment()
        private const val TAG = "DiscogsAlbumsFragment"
    }
}