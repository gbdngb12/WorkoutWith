package com.opensw.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opensw.mainscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val intent = Intent(this, MainActivity::class.java)

		setLoginFragment()
	}

	fun setLoginFragment() {
		val loginFragment: LoginFragment = LoginFragment()
		val transaction = supportFragmentManager.beginTransaction()
		transaction.add(R.id.MainFrameLayout, loginFragment)
		transaction.commit()
	}

	fun goRegisterFragment() {
		val registerFragment = RegisterFragment()
		val transaction = supportFragmentManager.beginTransaction()
		transaction.add(R.id.MainFrameLayout, registerFragment)
		transaction.addToBackStack("detail")
		transaction.commit()
	}

	fun goFindIdFragment() {
		val findIdFragment = FindIdFragment()
		val transaction = supportFragmentManager.beginTransaction()
		transaction.add(R.id.MainFrameLayout, findIdFragment)
		transaction.addToBackStack("detail")
		transaction.commit()
	}

	fun goFindPasswordFragment() {
		val findPasswordFragment = FindPasswordFragment()
		val transaction = supportFragmentManager.beginTransaction()
		transaction.add(R.id.MainFrameLayout, findPasswordFragment)
		transaction.addToBackStack("detail")
		transaction.commit()
	}
	fun goBack() {
		onBackPressed()
	}
}