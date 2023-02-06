package com.lukaszmaly.android.discogsalbums

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DiscogsAlbumsFragment: Fragment() {

    private lateinit var discogsAlbumsViewModel: DiscogsAlbumsViewModel
    private lateinit var photoRecyclerView: RecyclerView
    private lateinit var thumbnailDownloader: ThumbnailDownloader<ThumbnailHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true

        discogsAlbumsViewModel =
            ViewModelProvider(this)[DiscogsAlbumsViewModel::class.java]

        thumbnailDownloader = ThumbnailDownloader()
        lifecycle.addObserver((thumbnailDownloader))
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

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(
            thumbnailDownloader
        )
    }

    private class ThumbnailHolder(private val itemImageView: ImageView):
        RecyclerView.ViewHolder(itemImageView) {

        val bindDrawable: (Drawable) -> Unit = itemImageView::setImageDrawable
    }

    private inner class ThumbnailAdapter(private val releaseData: ReleaseData):
        RecyclerView.Adapter<ThumbnailHolder>() {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ThumbnailHolder {
                val view = layoutInflater.inflate(
                    R.layout.list_item_gallery,
                    parent,
                    false
                ) as ImageView
                return ThumbnailHolder(view)
            }

            override fun getItemCount(): Int {
                return 1
            }

            override fun onBindViewHolder(holder: ThumbnailHolder, position: Int) {
                val placeholder: Drawable = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.bean
                ) ?: ColorDrawable()
                holder.bindDrawable(placeholder)
            }
        }

    companion object {
        fun newInstance() = DiscogsAlbumsFragment()
    }
}