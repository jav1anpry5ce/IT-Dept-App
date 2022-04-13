package com.finalassignment.itdeptapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.finalassignment.itdeptapp.adapters.CourseAdapter
import com.finalassignment.itdeptapp.databinding.FragmentCoursesBinding
import com.finalassignment.itdeptapp.sqlite.DBAdapter

class CoursesFragment : BaseFragment<FragmentCoursesBinding>() {

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCoursesBinding {
		return FragmentCoursesBinding.inflate(inflater, container, false)
	}

	override fun initView() {
		val dbAdapter = DBAdapter(mContext)
		dbAdapter.open()
		val list = dbAdapter.getCourseList()
		dbAdapter.close()
		mBinding.recyclerView.adapter = CourseAdapter(list)
	}
}