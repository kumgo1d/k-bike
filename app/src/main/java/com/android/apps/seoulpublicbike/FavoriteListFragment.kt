package com.android.apps.seoulpublicbike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.fragment_favorite_list.view.*

class FavoriteListFragment : Fragment() {
    var helper: FavoriteListItemHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        helper = Room.databaseBuilder(requireContext(), FavoriteListItemHelper::class.java, "favorite_list")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)

        val adapter = FavoriteAdapter()
        adapter.helper = helper
        adapter.listItem = helper?.FavoriteListItemDAO()?.getAll() ?: mutableListOf()

        view.favorite_list_view.adapter = adapter
        view.favorite_list_view.layoutManager = LinearLayoutManager(activity)

        return view
    }
}