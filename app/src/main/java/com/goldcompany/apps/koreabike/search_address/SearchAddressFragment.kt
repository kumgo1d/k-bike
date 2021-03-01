package com.goldcompany.apps.koreabike.search_address


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.goldcompany.apps.koreabike.KBikeApplication
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBinding
import com.goldcompany.apps.koreabike.db.KBikeDatabase
import com.goldcompany.apps.koreabike.db.item.UserAddress
import com.goldcompany.apps.koreabike.find_places.FindPlaces
import com.goldcompany.apps.koreabike.find_places.kakaodata.KakaoData
import kotlinx.coroutines.coroutineScope

class SearchAddressFragment : Fragment() {
    private lateinit var binding: FragmentSearchAddressBinding

    private lateinit var viewModel: SearchAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_address, container, false)

        viewModel = ViewModelProvider(this).get(SearchAddressViewModel::class.java)

        addListener()

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun addListener() {
        binding.parentLayout.setOnTouchListener { _, _ ->
            hideKeyboard()
            return@setOnTouchListener true
        }

        binding.navigationBackButton.setOnClickListener {
            hideKeyboard()
            findNavController().popBackStack()
        }

        binding.searchAddressButton.setOnClickListener {
            searchAddress()
        }

        binding.favoriteAddressButton.setOnClickListener {
            findNavController().navigate(SearchAddressFragmentDirections.actionSearchAddressFragmentToFavoritePlaceFragment())
        }
    }

    private fun searchAddress() {
        val address = binding.searchAddressInput.text.toString()
        FindPlaces().callKakaoKeyword(address = address) { data, _ ->
            if(data == null) {
                //TODO 에러 처리
                return@callKakaoKeyword
            }

            binding.searchAddressList.adapter = SearchAddressAdapter(data, viewModel)
        }

        hideKeyboard()
    }

    private fun hideKeyboard() {
        binding.searchAddressInput.clearFocus()

        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}

class SearchAddressAdapter(private val dataSet: KakaoData,
                           private val viewModel: SearchAddressViewModel): RecyclerView.Adapter<SearchAddressAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val keyword: TextView = view.findViewById(R.id.item_keyword)
        val address: TextView = view.findViewById(R.id.item_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_search_address_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.keyword.text = dataSet.documents[position].place_name
        holder.address.text = dataSet.documents[position].address_name

        holder.itemView.setOnClickListener {
            val userAddress = UserAddress(
                latitude = dataSet.documents[position].y.toDouble(),
                longitude = dataSet.documents[position].x.toDouble(),
                address = dataSet.documents[position].address_name,
                keyword = dataSet.documents[position].place_name,
                selected = true
            )

            viewModel.insertAddress(userAddress)
        }
    }

    override fun getItemCount() = dataSet.documents.size
}