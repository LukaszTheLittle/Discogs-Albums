package com.lukaszmaly.android.discogsalbums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DiscogsAlbumsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discogs_albums)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, DiscogsAlbumsFragment.newInstance())
                .commit()
        }
    }
}