package com.opensw.mainscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opensw.mainscreen.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
	var mainActivity: MainActivity? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?

	): View? {
		val intent = Intent(activity, MajorScreen::class.java)
		val binding = FragmentLoginBinding.inflate(inflater, container, false)
		binding.btnRegister.setOnClickListener { mainActivity?.goRegisterFragment() }
		binding.btnFindId.setOnClickListener { mainActivity?.goFindIdFragment() }
		binding.btnFindPassword.setOnClickListener { mainActivity?. goFindPasswordFragment() }
		binding.btnLogin.setOnClickListener { mainActivity?.startActivity(intent) }
		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)

		if (context is MainActivity) mainActivity = context
	}
}