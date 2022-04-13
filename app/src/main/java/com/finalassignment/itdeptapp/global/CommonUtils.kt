package com.finalassignment.itdeptapp.global

import android.content.Context
import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.finalassignment.itdeptapp.BuildConfig
import com.finalassignment.itdeptapp.R

fun Context.showToast(resId: Int) {
	showToast(getString(resId))
}

fun Context.showToast(msg: String, vararg args: Any) {
	val message = String.format(msg, *args)
	Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun myLog(message: String, vararg args: Any) {
	try {
		if (BuildConfig.DEBUG && BuildConfig.PRINT_LOG) {
			Log.d("MyLog", String.format(message, *args))
		}
	} catch (e: Exception) {
		e.printStackTrace()
	}
}

fun Context.showImage(imgView: ImageView, path: Any) {
	var options = RequestOptions().error(R.drawable.ic_default_image)
	var isFromUrl = false
	if (path is String) {
		if (path.isEmpty()) {
			return
		}
		if (path.startsWith("http://") || path.startsWith("https://")) {
			isFromUrl = true
		}
	}
	val circularProgressDrawable = CircularProgressDrawable(this)
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
		circularProgressDrawable.colorFilter = BlendModeColorFilter(ContextCompat.getColor(this, R.color.progress_color), BlendMode.SRC_ATOP)
	} else {
		@Suppress("DEPRECATION")
		circularProgressDrawable.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP)
	}
	circularProgressDrawable.strokeWidth = 8f
	circularProgressDrawable.centerRadius = 40f
	if (isFromUrl) {
		circularProgressDrawable.start()
		options = options.placeholder(circularProgressDrawable)
		options = options.diskCacheStrategy(DiskCacheStrategy.ALL)
	} else {
		options = options.diskCacheStrategy(DiskCacheStrategy.NONE).signature(ObjectKey(System.currentTimeMillis()))
	}
	try {
		GlideApp.with(this)
			.setDefaultRequestOptions(options)
			.load(path)
			.listener(object : RequestListener<Drawable> {
				override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
					circularProgressDrawable.stop()
					return false
				}
				override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
					circularProgressDrawable.stop()
					return false
				}
			})
			.thumbnail(0.3f)
			.transition(DrawableTransitionOptions.withCrossFade())
			.into(imgView)
	} catch (e: Exception) {
		e.printStackTrace()
	}
}

// Go to other Activity WITHOUT finish current activity
fun <T : AppCompatActivity> Context.gotoActivity(activity: Class<T>, vararg args: Pair<String, Any>) {
	val intent = Intent(this, activity)
	args.forEach {
		when (val value = it.second) {
			is Int -> intent.putExtra(it.first, value)
			is String -> intent.putExtra(it.first, value)
			is Boolean -> intent.putExtra(it.first, value)
			is Long -> intent.putExtra(it.first, value)
			is Float -> intent.putExtra(it.first, value)
			is Double -> intent.putExtra(it.first, value)
			is Array<*> -> intent.putExtra(it.first, value)
		}
	}
	startActivity(intent)
}