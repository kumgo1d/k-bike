package com.android.apps.seoulpublicbike.favoritelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.android.apps.seoulpublicbike.databinding.FragmentFavoriteListBinding
import com.android.apps.seoulpublicbike.seoul.SeoulMapViewModel
import com.android.apps.seoulpublicbike.seoul.SeoulMapViewModelFactory

class FavoriteListFragment : Fragment() {
    lateinit var seoulMapViewModel: SeoulMapViewModel

    var helper: FavoriteListItemHelper? = null
    var list = mutableListOf<FavoriteListItem>()

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        helper = Room.databaseBuilder(requireContext(), FavoriteListItemHelper::class.java, "favorite_list")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        findChangedBikeData()

        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)

        val adapter = FavoriteListAdapter()
        adapter.helper = helper
        adapter.listItem = list

        binding.favoriteListView.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(activity)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun findChangedBikeData() {
        list = helper?.FavoriteListItemDAO()?.getAll() ?: mutableListOf()
        seoulMapViewModel = ViewModelProvider(this, SeoulMapViewModelFactory())
            .get(SeoulMapViewModel::class.java)
        
        seoulMapViewModel.getBikes1()!!.observe(viewLifecycleOwner, Observer { bike ->
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
        seoulMapViewModel.getBikes2()!!.observe(viewLifecycleOwner, Observer { bike ->
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
        seoulMapViewModel.getBikes3()!!.observe(viewLifecycleOwner, Observer { bike ->
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