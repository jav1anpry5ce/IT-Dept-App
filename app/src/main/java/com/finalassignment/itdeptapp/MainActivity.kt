package com.finalassignment.itdeptapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.finalassignment.itdeptapp.databinding.ActivityMainBinding
import com.finalassignment.itdeptapp.global.showToast
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

	companion object {
		const val BACK_PRESS_TIME_INTERVAL = 3000
		const val HOD_EMAIL_ADDRESS	= "ithod@ucc.edu.jm "
	}

	private lateinit var mBinding: ActivityMainBinding
	private lateinit var mAppBarConfiguration: AppBarConfiguration

	private var mPrevBackPressed: Long = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		mBinding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(mBinding.root)

		setSupportActionBar(mBinding.appBarMain.toolbar)

		mBinding.appBarMain.fab.setOnClickListener {
			val intent = Intent(Intent.ACTION_SENDTO)
			intent.data = Uri.parse("mailto:$HOD_EMAIL_ADDRESS")
			try {
				startActivity(Intent.createChooser(intent, "Choose Email Client..."))
			} catch (e: Exception) {
				showToast(e.localizedMessage ?: "Unexpected error occurs!")
			}
		}
		val drawerLayout: DrawerLayout = mBinding.drawerLayout
		val navView: NavigationView = mBinding.navView
		val navController = findNavController(R.id.nav_host_fragment_content_main)
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = AppBarConfiguration(setOf(R.id.navDirectory, R.id.navCourses, R.id.navTimetable, R.id.navAdmissions, R.id.navSocialMedia), drawerLayout)
		setupActionBarWithNavController(navController, mAppBarConfiguration)
		navView.setupWithNavController(navController)
	}

	fun showFab() {
		val fab = findViewById<FloatingActionButton>(R.id.fab)
		val layoutParams = fab.layoutParams
		if (layoutParams is CoordinatorLayout.LayoutParams) {
			val behavior = layoutParams.behavior
			if (behavior is HideBottomViewOnScrollBehavior) {
				behavior.slideUp(fab)
			}
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.nav_host_fragment_content_main)
		return navController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
	}

	override fun onBackPressed() {
		if (System.currentTimeMillis() - mPrevBackPressed < BACK_PRESS_TIME_INTERVAL) {
			finish()
		} else {
			showToast("Press again to exit.")
			mPrevBackPressed = System.currentTimeMillis()
		}
	}
}