package com.goldcompany.apps.koreabike.favoritelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.goldcompany.apps.koreabike.databinding.FragmentFavoriteListBinding
import com.goldcompany.apps.koreabike.seoul.SeoulMapViewModel
import com.goldcompany.apps.koreabike.seoul.SeoulMapViewModelFactory

class FavoriteListFragment : Fragment() {
    lateinit var seoulMapViewModel: SeoulMapViewModel

    var helper: FavoriteListItemHelper? = null
    var list = mutableListOf<FavoriteListItem>()

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
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)

        seoulMapViewModel = ViewModelProvider(this, SeoulMapViewModelFactory())
            .get(SeoulMapViewModel::class.java)

        findChangedBikeData()

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

        seoulMapViewModel.getBikes1()!!.observe(viewLifecycleOwner, { bike ->
            for(b in bike!!.getStationListHist.row) {
                for(item in list) {
                    if(b.stationName == item.station) {
                        if(b.parkingBikeTotCnt != item.parkingBike || b.rackTotCnt != item.rackBike) {
                            item.rackBike = b.rackTotCnt
                            item.parkingBike = b.parkingBikeTotCnt
                        }
                    }
                }
            }
        })

        seoulMapViewModel.getBikes2()!!.observe(viewLifecycleOwner, { bike ->
            for(b in bike!!.getStationListHist.row) {
                for(item in list) {
                    if(b.stationName == item.station) {
                        if(b.parkingBikeTotCnt != item.parkingBike || b.rackTotCnt != item.rackBike) {
                            item.rackBike = b.rackTotCnt
                            item.parkingBike = b.parkingBikeTotCnt
                        }
                    }
                }
            }
        })

        seoulMapViewModel.getBikes3()!!.observe(viewLifecycleOwner, { bike ->
            for(b in bike!!.getStationListHist.row) {
                for(item in list) {
                    if(b.stationName == item.station) {
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