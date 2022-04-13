package com.finalassignment.itdeptapp.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DBAdapter.DATABASE_NAME, null, DBAdapter.DATABASE_VERSION) {

	override fun onCreate(db: SQLiteDatabase?) {
		db?.execSQL(DBAdapter.QUERY_CREATE_USER)
		db?.execSQL(DBAdapter.QUERY_CREATE_COURSE)
		db?.execSQL(DBAdapter.QUERY_CREATE_TIMETABLE)
	}

	override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
		db?.execSQL(DBAdapter.QUERY_UPGRADE_USER)
		db?.execSQL(DBAdapter.QUERY_UPGRADE_COURSE)
		db?.execSQL(DBAdapter.QUERY_UPGRADE_TIMETABLE)
		onCreate(db)
	}
}