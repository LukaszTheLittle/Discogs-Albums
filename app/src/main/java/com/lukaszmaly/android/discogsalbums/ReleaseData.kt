package com.lukaszmaly.android.discogsalbums

data class ReleaseData(
    var id: String = "",                // release id
    var year: String = "",              // release year
    var resource_url: String = "",      // release url
    var artists_sort: String = "",      // artist name
    var title: String = "",             // release title
    var thumb: String = ""              // thumb url
)