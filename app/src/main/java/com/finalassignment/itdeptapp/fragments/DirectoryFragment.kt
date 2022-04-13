package com.finalassignment.itdeptapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.finalassignment.itdeptapp.adapters.UserAdapter
import com.finalassignment.itdeptapp.databinding.FragmentDirectoryBinding
import com.finalassignment.itdeptapp.sqlite.DBAdapter

class DirectoryFragment : BaseFragment<FragmentDirectoryBinding>() {

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDirectoryBinding {
		return FragmentDirectoryBinding.inflate(inflater, container, false)
	}

	override fun initView() {
		val dbAdapter = DBAdapter(mContext)
		dbAdapter.open()
		val list = dbAdapter.getUserList()
		dbAdapter.close()
		mBinding.recyclerView.adapter = UserAdapter(list)
	}
}