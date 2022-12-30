package com.lukaszmaly.android.discogsalbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class DiscogsAlbumsViewModel: ViewModel() {

    val releaseDataLiveData: LiveData<ReleaseData>

    init {
        releaseDataLiveData = DiscogsFetch().fetchContents()
    }
}