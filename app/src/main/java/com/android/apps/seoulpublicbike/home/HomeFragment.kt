package com.android.apps.seoulpublicbike.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.apps.seoulpublicbike.R
import com.android.apps.seoulpublicbike.base.BaseFragment
import com.android.apps.seoulpublicbike.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        navSeoulFragment()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

//    private fun addListener() {
//        binding.seoulButton.setOnClickListener {
//            navSeoulFragment()
//        }
//    }
//
    private fun navSeoulFragment() {
        val f = BaseFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.attach(f).commit()
    }
}