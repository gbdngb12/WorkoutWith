package com.opensw.mainscreen.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.opensw.mainscreen.MatchingStartFragment
import com.opensw.mainscreen.R
import com.opensw.mainscreen.databinding.ActivityMajorScreenBinding

class MajorScreen : AppCompatActivity() {

	private lateinit var mBinding : ActivityMajorScreenBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		mBinding = ActivityMajorScreenBinding.inflate(layoutInflater)

		setContentView(mBinding.root)

		val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

		val navController = navHostFragment.navController

		NavigationUI.setupWithNavController(mBinding.majorScreenNav, navController)


	}

	fun hideNavBar() {
		mBinding.majorScreenNav.isVisible = false
	}

	fun showNavBar() {
		mBinding.majorScreenNav.isVisible = true
	}

	fun goMatchingFragment() {
		val matchingStartFragment = MatchingStartFragment()
		val transaction = supportFragmentManager.beginTransaction()
		transaction.add(R.id.LinearLay, matchingStartFragment)
		transaction.addToBackStack("")
		transaction.commit()
	}

	fun goBack() {
		onBackPressed()
	}
}