package com.goldcompany.apps.koreabike.ui.favorite_bike

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentFavoriteListBinding
import com.goldcompany.apps.koreabike.db.item.FavoriteListItem
import com.goldcompany.apps.koreabike.data.seoul.SeoulBike
import kotlinx.android.synthetic.main.sub_favorite_list_item.view.*
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class FavoriteBikeListFragment : Fragment() {
    private lateinit var viewModel: FavoriteBikeListViewModel
    private lateinit var binding: FragmentFavoriteListBinding

    private var list = mutableListOf<FavoriteListItem>()

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
            bike.stationList.stationInfo.forEach { bike ->
                list.filter { item ->
                    bike.stationName == item.station
                }.filter { item ->
                    bike.parkingBikeTotCnt != item.parkingBike
                }.filter { item ->
                    bike.rackTotCnt != item.rackBike
                }.map { item ->
                    item.rackBike = bike.rackTotCnt
                    item.parkingBike = bike.parkingBikeTotCnt
                    viewModel.insertItem(item)
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

class FavoriteBikeListAdapter(private val list: MutableList<FavoriteListItem>,
                              private val deleteItem: (FavoriteListItem) -> Unit) : RecyclerView.Adapter<FavoriteBikeListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_favorite_list_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list[position]
        holder.setList(data)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var currentItem: FavoriteListItem? = null

        init {
            itemView.delete_button.setOnClickListener {
                deleteItem(currentItem!!)
                list.remove(currentItem)
                notifyDataSetChanged()
            }
        }

        @SuppressLint("SetTextI18n")
        fun setList(item: FavoriteListItem) {
            itemView.item_station.text = item.station
            itemView.item_parking.text = "자전거 : ${item.parkingBike}"
            itemView.item_rack.text = "주차가능 : ${item.rackBike}"

            this.currentItem = item
        }
    }
}