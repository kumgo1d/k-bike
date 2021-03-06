package com.goldcompany.apps.koreabike.favorite_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.goldcompany.apps.koreabike.databinding.FragmentFavoriteListBinding
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.seoul_bike_data.SeoulBike
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class FavoriteListFragment : Fragment() {
    private lateinit var viewModel: FavoriteListViewModel
    private lateinit var binding: FragmentFavoriteListBinding

    var list = mutableListOf<FavoriteListItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FavoriteListViewModel::class.java)

        viewModel.getList().observe(viewLifecycleOwner) {
            list = it
            findChangedBikeData()
            binding.favoriteListView.adapter = FavoriteListAdapter(list, ::deleteItem)
        }

        return binding.root
    }

    private fun deleteItem(item: FavoriteListItem) {
        viewModel.deleteListItem(item)
    }

    private fun findChangedBikeData() {
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
        try {
            for(b in bike.stationList.stationInfo) {
                for(item in list) {
                    if(b.stationName == item.station) {
                        if(b.parkingBikeTotCnt != item.parkingBike || b.rackTotCnt != item.rackBike) {
                            item.rackBike = b.rackTotCnt
                            item.parkingBike = b.parkingBikeTotCnt
                            viewModel.insertItem(item)
                        }
                    }
                }
            }
        } catch(e: NullPointerException) {
            //TODO 서울 api null값에 대한 dialog
        }
    }
}