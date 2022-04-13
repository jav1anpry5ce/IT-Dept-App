package com.finalassignment.itdeptapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finalassignment.itdeptapp.databinding.ItemCourseBinding
import com.finalassignment.itdeptapp.structures.CourseInfo

class CourseAdapter(private val mList: MutableList<CourseInfo>) : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

	inner class ViewHolder(val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root)

	override fun getItemCount() : Int {
		return mList.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
		return ViewHolder(ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val info = mList[position]
		holder.binding.txtCode.text = info.code
		holder.binding.txtCredits.text = info.credits.toString()
		holder.binding.txtTitle.text = info.title
		holder.binding.txtPreRequisites.text = "Pre-requisites: ${info.pre_requisites}"
		holder.binding.expandableTextView.text = info.description
		holder.binding.btnExpand.setOnClickListener {
			holder.binding.expandableTextView.toggle()
			holder.binding.btnExpand.animate().rotationBy(180f).setDuration(500).start()
		}
	}
}