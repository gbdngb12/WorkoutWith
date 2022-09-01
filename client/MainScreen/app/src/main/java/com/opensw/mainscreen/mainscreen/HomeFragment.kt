package com.opensw.mainscreen.mainscreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.opensw.mainscreen.R
import com.opensw.mainscreen.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var majorScreen: MajorScreen
    private var mBinding : FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        mBinding = binding
        val data:MutableList<Memo> = loadData()
        var adapter = CustomAdapter()
        adapter.listData = data
        binding.RecyclerHome.adapter = adapter
        binding.RecyclerHome.layoutManager = LinearLayoutManager(majorScreen)
        binding.btnGoMatching.setOnClickListener {
            majorScreen?.goMatchingFragment()
            majorScreen?.hideNavBar()
        }
        return mBinding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        majorScreen = context as MajorScreen
    }
    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    fun loadData() : MutableList<Memo> {

        val data:MutableList<Memo> = mutableListOf()
        for(no in 1..100){
            // 개별 데이터 생성
            val title = "OO동 OO모임 ${no+1}"
            val date = System.currentTimeMillis()
            // 100 개의 Memo 클래스를 생성
            var memo = Memo(no, title, date)
            // 데이터 배열에 담는다
            data.add(memo)
        }
        return data;
    }
}