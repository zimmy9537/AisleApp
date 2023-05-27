package com.zimmy.sample.aisleapp.ui.main.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zimmy.sample.aisleapp.databinding.FragmentMatchesBinding

class MatchesFragment : Fragment() {

    private lateinit var binding: FragmentMatchesBinding

    companion object {
        fun newInstance() = MatchesFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchesBinding.inflate(layoutInflater)
        return binding.root
    }
}