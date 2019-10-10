package com.example.tutorial4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ArtistListFragment.OnArtistSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, ArtistListFragment.newInstance(), "artistList")
                .commit()
        }
    }

    override fun onArtistSelected(artist: Artist) {
        val detailsFragment =
            ArtistDetailsFragment.newInstance(artist)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "artistDetails")
            .addToBackStack(null)
            .commit()
    }
}
