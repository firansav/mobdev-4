package com.example.tutorial4

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tutorial4.databinding.RecyclerItemArtistsBinding


class ArtistListFragment : Fragment() {

    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var urls: Array<String>
    private lateinit var listener: OnArtistSelected

    companion object {

        fun newInstance(): ArtistListFragment {
            return ArtistListFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnArtistSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnArtistSelected.")
        }

        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)
        urls = resources.getStringArray(R.array.urls)

        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_artist_list, container,
            false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = ArtistListAdapter(activity)
        return view
    }

    internal inner class ArtistListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerArtistBinding =
                RecyclerItemArtistsBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerArtistBinding.root, recyclerArtistBinding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val artist = Artist(imageResIds[position], names[position],
                descriptions[position], urls[position])
            viewHolder.setData(artist)
            viewHolder.itemView.setOnClickListener { listener.onArtistSelected(artist) }
        }

        override fun getItemCount() = names.size
    }

    internal inner class ViewHolder constructor(itemView: View,
                                                private val recyclerItemArtistListBinding:
                                                RecyclerItemArtistsBinding
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(artist: Artist) {
            recyclerItemArtistListBinding.artist = artist
        }
    }

    interface OnArtistSelected {
        fun onArtistSelected(artist: Artist)
    }

}
