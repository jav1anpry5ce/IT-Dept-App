package com.finalassignment.itdeptapp.fragments

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.finalassignment.itdeptapp.databinding.FragmentSocialMediaBinding


class SocialMediaFragment : BaseFragment<FragmentSocialMediaBinding>() {

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSocialMediaBinding {
		return FragmentSocialMediaBinding.inflate(inflater, container, false)
	}

	override fun initView() {
		mBinding.btnFacebook.setOnClickListener { openSocialMedia(0) }
		mBinding.btnTwitter.setOnClickListener { openSocialMedia(1) }
		mBinding.btnInstagram.setOnClickListener { openSocialMedia(2) }
	}

	private fun openSocialMedia(type: Int) {
		val url = when (type) {
			1 -> "https://twitter.com/UCCjamaica"
			2 -> "https://www.instagram.com/uccjamaica"
			else -> "https://www.facebook.com/uccjamaica"
		}
		val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
		startActivity(intent)
	}
}