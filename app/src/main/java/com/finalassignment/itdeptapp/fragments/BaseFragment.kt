package com.finalassignment.itdeptapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.finalassignment.itdeptapp.MainActivity

abstract class BaseFragment<BINDING : ViewBinding> : Fragment() {

	lateinit var mBinding: BINDING
	lateinit var mContext: MainActivity

	abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?) : BINDING
	abstract fun initView()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		mBinding = getBinding(inflater, container)
		mContext = activity as MainActivity
		initView()
		return mBinding.root
	}

	override fun onResume() {
		super.onResume()
		mBinding.root.postDelayed({
			mContext.showFab()
		}, 50)
	}
}