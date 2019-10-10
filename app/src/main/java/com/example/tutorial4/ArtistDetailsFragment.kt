package com.example.tutorial4

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tutorial4.databinding.FragmentArtistDetailsBinding

//1
class ArtistDetailsFragment : Fragment() {

    //2
    companion object {

        private const val ARTIST = "model"

        fun newInstance(artist: Artist): ArtistDetailsFragment {
            val args = Bundle()
            args.putSerializable(ARTIST, artist)
            val fragment = ArtistDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    //3
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentArtistDetailsBinding =
            FragmentArtistDetailsBinding.inflate(inflater, container, false)

        val model = arguments!!.getSerializable(ARTIST) as Artist
        fragmentArtistDetailsBinding.artist = model
        model.text = String.format(getString(R.string.description_format), model.description, model.url)
        return fragmentArtistDetailsBinding.root
    }

}
