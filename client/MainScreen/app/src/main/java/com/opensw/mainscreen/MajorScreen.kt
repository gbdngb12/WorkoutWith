package com.opensw.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.opensw.mainscreen.databinding.ActivityMajorScreenBinding

class MajorScreen : AppCompatActivity() {

	val binding by lazy { ActivityMajorScreenBinding.inflate(layoutInflater) }
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val fragmentList = listOf(HomeScreenFragment(), RankingScreenFragment(), CommunityScreenFragment(), MyProfileScreenFragment())
		val adapter = MainScreenFragmentAdapter(this)
		adapter.fragmentList = fragmentList

		binding.viewMainScreenPager.adapter = adapter
		val tabTitles = listOf<String>("메인", "랭킹", "커뮤니티", "내 프로필")
		TabLayoutMediator(binding.TabLayout, binding.viewMainScreenPager) { tab, position ->
			tab.text = tabTitles[position]
		}.attach()
	}

	fun goMatchingFragment() {
		val matchingStartFragment = MatchingStartFragment()
		val transaction = supportFragmentManager.beginTransaction()
		transaction.add(R.id.MainFrameLayout, matchingStartFragment)
		transaction.addToBackStack("detail")
		transaction.commit()
	}
}