package com.android.apps.seoulpublicbike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.android.apps.seoulpublicbike.seoul.SeoulMapViewModel
import com.android.apps.seoulpublicbike.seoul.SeoulMapViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_list.view.*

class FavoriteListFragment : Fragment() {
    lateinit var seoulMapViewModel: SeoulMapViewModel

    var helper: FavoriteListItemHelper? = null
    var list = mutableListOf<FavoriteListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        helper = Room.databaseBuilder(requireContext(), FavoriteListItemHelper::class.java, "favorite_list")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        findChangedBikeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)

        val adapter = FavoriteListAdapter()
        adapter.helper = helper
        adapter.listItem = list

        view.favorite_list_view.adapter = adapter
        view.favorite_list_view.layoutManager = LinearLayoutManager(activity)

        return view
    }

    private fun findChangedBikeData() {
        list = helper?.FavoriteListItemDAO()?.getAll() ?: mutableListOf()
        seoulMapViewModel = ViewModelProvider(this, SeoulMapViewModelFactory()).get(
            SeoulMapViewModel::class.java)
        seoulMapViewModel.getBikes1()!!.observe(this, Observer { bike ->
            val bike1 = bike
            for(b in bike1!!.rentBikeStatus.row) {
                for(item in list) {
                    if(b.stationName.split(".")[0].toLong() == item.no) {
                        if(b.parkingBikeTotCnt != item.parkingBike || b.rackTotCnt != item.rackBike) {
                            item.rackBike = b.rackTotCnt
                            item.parkingBike = b.parkingBikeTotCnt
                        }
                    }
                }
            }
        })
        seoulMapViewModel = ViewModelProvider(this, SeoulMapViewModelFactory()).get(
            SeoulMapViewModel::class.java)
        seoulMapViewModel.getBikes2()!!.observe(this, Observer { bike ->
            val bike2 = bike
            for(b in bike2!!.rentBikeStatus.row) {
                for(item in list) {
                    if(b.stationName.split(".")[0].toLong() == item.no) {
                        if(b.parkingBikeTotCnt != item.parkingBike || b.rackTotCnt != item.rackBike) {
                            item.rackBike = b.rackTotCnt
                            item.parkingBike = b.parkingBikeTotCnt
                        }
                    }
                }
            }
        })
        seoulMapViewModel = ViewModelProvider(this, SeoulMapViewModelFactory()).get(
            SeoulMapViewModel::class.java)
        seoulMapViewModel.getBikes3()!!.observe(this, Observer { bike ->
            val bike3 = bike
            for(b in bike3!!.rentBikeStatus.row) {
                for(item in list) {
                    if(b.stationName.split(".")[0].toLong() == item.no) {
                        if(b.parkingBikeTotCnt != item.parkingBike || b.rackTotCnt != item.rackBike) {
                            item.rackBike = b.rackTotCnt
                            item.parkingBike = b.parkingBikeTotCnt
                        }
                    }
                }
            }
        })


    }
}