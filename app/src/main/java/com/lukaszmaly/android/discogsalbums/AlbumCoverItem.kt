package com.lukaszmaly.android.discogsalbums

data class AlbumCoverItem(
    var id: String = "",                // release id
    var name: String = "",              // artist name
    var title: String = "",             // album title
    var resource_url: String = ""       // cover photo url
)