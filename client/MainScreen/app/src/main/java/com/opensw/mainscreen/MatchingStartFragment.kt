package com.opensw.mainscreen

import android.content.Context
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.opensw.mainscreen.databinding.FragmentMatchingStartBinding

class MatchingStartFragment : Fragment() {

    lateinit var majorScreen: MajorScreen
    lateinit var binding: FragmentMatchingStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchingStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        majorScreen = context as MajorScreen
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = listOf("- 선택하세요 -", "서울", "대전", "대구", "부산")
        //var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        //binding.spinner.adapter = adapter


    }
}