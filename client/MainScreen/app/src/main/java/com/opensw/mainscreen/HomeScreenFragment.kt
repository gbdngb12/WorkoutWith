package com.opensw.mainscreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.opensw.mainscreen.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : Fragment() {

    lateinit var majorScreen: MajorScreen
    lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        //binding.btnMatchingStart.setOnClickListener { majorScreen?.goMatchingFragment() }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MajorScreen) majorScreen = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}