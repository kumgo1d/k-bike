package com.goldcompany.apps.koreabike.ui.favorite_bike

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.goldcompany.apps.koreabike.databinding.FragmentFavoriteListBinding
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.data.seoul.SeoulBike
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class FavoriteBikeListFragment : Fragment() {
    private lateinit var viewModel: FavoriteBikeListViewModel
    private lateinit var binding: FragmentFavoriteListBinding

    var list = mutableListOf<FavoriteListItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FavoriteBikeListViewModel::class.java)

        setList()

        return binding.root
    }

    private fun setList() {
        viewModel.getList().observe(viewLifecycleOwner) {
            list = it
            findChangedBikeData()
            binding.favoriteListView.adapter = FavoriteBikeListAdapter(list, ::deleteItem)
        }
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
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setMessage("인터넷 연결 혹은 서울 공공 데이터에 문제가 생겼습니다.\n" +
                            "Web으로 이용해주세요. 감사합니다.")
                    setPositiveButton("확인") { dialog, _ ->
                        dialog.dismiss()
                    }
                }
                builder.create()
            }
            alertDialog!!.show()
        }
    }
}