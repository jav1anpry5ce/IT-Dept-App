package com.finalassignment.itdeptapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalassignment.itdeptapp.databinding.ItemDirectoryBinding
import com.finalassignment.itdeptapp.global.showImage
import com.finalassignment.itdeptapp.structures.UserInfo

class UserAdapter(private val mList: MutableList<UserInfo>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

	inner class ViewHolder(val binding: ItemDirectoryBinding) : RecyclerView.ViewHolder(binding.root)

	override fun getItemCount() : Int {
		return mList.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
		return ViewHolder(ItemDirectoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val info = mList[position]
		val context = holder.itemView.context
		context.showImage(holder.binding.imgPhoto, info.photo)
		holder.binding.txtName.text = info.name
		holder.binding.txtEmail.text = info.email
		holder.binding.txtPhone.text = info.phone
	}
}