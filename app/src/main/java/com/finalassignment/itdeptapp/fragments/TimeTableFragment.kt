package com.finalassignment.itdeptapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.finalassignment.itdeptapp.adapters.TimeTableAdapter
import com.finalassignment.itdeptapp.databinding.FragmentTimetableBinding
import com.finalassignment.itdeptapp.sqlite.DBAdapter

class TimeTableFragment : BaseFragment<FragmentTimetableBinding>() {

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTimetableBinding {
		return FragmentTimetableBinding.inflate(inflater, container, false)
	}

	override fun initView() {
		val dbAdapter = DBAdapter(mContext)
		dbAdapter.open()
		val list = dbAdapter.getTimeTableList()
		dbAdapter.close()
		mBinding.recyclerView.adapter = TimeTableAdapter(list)
	}
}