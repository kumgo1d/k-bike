package com.goldcompany.apps.koreabike.ui.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.goldcompany.apps.koreabike.R
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
    private lateinit var binding: FragmentNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_navigation, container, false)

        setListener()

        return binding.root
    }

    private fun setListener() {
        binding.navigationAppBar.navigationBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}