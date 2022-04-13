package com.finalassignment.itdeptapp.global

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyGlideModule : AppGlideModule() {

	override fun applyOptions(context: Context, builder: GlideBuilder) {
		val memoryCacheSizeBytes = 100 * 1024 * 1024L // 100 MB
		builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes))
	}

}