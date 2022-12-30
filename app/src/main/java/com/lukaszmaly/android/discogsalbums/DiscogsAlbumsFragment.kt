package com.lukaszmaly.android.discogsalbums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
                photoRecyclerView.adapter = ThumbnailAdapter(releaseData)
            }
        )
    }

    private class ThumbnailHolder(itemTextView: TextView):
        RecyclerView.ViewHolder(itemTextView) {

        val bindTitle: (CharSequence) -> Unit = itemTextView::setText
    }

    private class ThumbnailAdapter(private val releaseData: ReleaseData):
        RecyclerView.Adapter<ThumbnailHolder>() {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ThumbnailHolder {
                val textView = TextView(parent.context)
                return ThumbnailHolder(textView)
            }

        override fun getItemCount(): Int {
            return 1
        }

            override fun onBindViewHolder(holder: ThumbnailHolder, position: Int) {
                holder.bindTitle(releaseData.title)
            }
        }

    companion object {
        fun newInstance() = DiscogsAlbumsFragment()
    }
}