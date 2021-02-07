package com.goldcompany.apps.koreabike.favorite_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.goldcompany.apps.koreabike.databinding.FragmentFavoriteListBinding
import com.goldcompany.apps.koreabike.seoul.SeoulMapViewModel
import com.goldcompany.apps.koreabike.seoul.SeoulMapViewModelFactory
import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike
import kotlinx.coroutines.launch

class FavoriteListFragment : Fragment() {
    private lateinit var viewModel: SeoulMapViewModel
    private lateinit var binding: FragmentFavoriteListBinding

    var helper: FavoriteListItemHelper? = null
    var list = mutableListOf<FavoriteListItem>()

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
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, SeoulMapViewModelFactory())
            .get(SeoulMapViewModel::class.java)

        findChangedBikeData()

        addListener()

        return binding.root
    }

    private fun addListener() {
        val adapter = FavoriteListAdapter()
        adapter.helper = helper
        adapter.listItem = list

        binding.favoriteListView.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun findChangedBikeData() {
        list = helper?.FavoriteListItemDAO()?.getAll() ?: mutableListOf()

        lifecycleScope.launch {
            viewModel.getBikes1().observe(viewLifecycleOwner, { bike ->
                observeBike(bike)
            })

            viewModel.getBikes2().observe(viewLifecycleOwner, { bike ->
                observeBike(bike)
            })

            viewModel.getBikes3().observe(viewLifecycleOwner, { bike ->
                observeBike(bike)
            })
        }
    }

    private fun observeBike(bike: SeoulBike) {
        for(b in bike.stationList.stationInfo) {
            for(item in list) {
                if(b.stationName == item.station) {
                    if(b.parkingBikeTotCnt != item.parkingBike || b.rackTotCnt != item.rackBike) {
                        item.rackBike = b.rackTotCnt
                        item.parkingBike = b.parkingBikeTotCnt
                    }
                }
            }
        }
    }
}