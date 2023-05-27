package com.zimmy.sample.aisleapp.ui.main.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zimmy.sample.aisleapp.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding

    companion object {
        fun newInstance() = DiscoverFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoverBinding.inflate(layoutInflater)
        return binding.root
    }

}