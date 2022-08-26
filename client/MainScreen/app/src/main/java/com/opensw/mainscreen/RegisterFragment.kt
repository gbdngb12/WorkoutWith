package com.opensw.mainscreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opensw.mainscreen.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
	lateinit var mainActivity: MainActivity

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val binding = FragmentRegisterBinding.inflate(inflater, container, false)
		binding.btnRegisterConfirm.setOnClickListener { mainActivity.goBack() }
		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		mainActivity = context as MainActivity
	}
}