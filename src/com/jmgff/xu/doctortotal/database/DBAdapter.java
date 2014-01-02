package com.jmgff.xu.doctortotal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAdapter {
	private Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		context = ctx;
		DBHelper = new DatabaseHelper(context); // 多例模式
		open();
	}

	public SQLiteDatabase open() throws SQLException {
		db = DBHelper.openDatabase();
		return db;
	}

	public void close() {
		if (db.isOpen()) {
			db.close();
			db = null;
		}
	}

	public Cursor select(String sql, String[] selectionArgs) {
		return db.rawQuery(sql, selectionArgs);
	}

	public void execSQL(String sql) {
		db.execSQL(sql);
	}

	public void execSQL(String sql, Object[] bindArgs) {
		db.execSQL(sql, bindArgs);
	}

	public Cursor select(String table_name, String[] fields, String condition,
			String[] Param, String Group, String Having, String orderby) {

		while (db.isDbLockedByOtherThreads()) {
			Log.w("dblock", "db is locked by other threads!");
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return db.query(table_name, fields, condition, Param, Group, Having,
				orderby);
	}

	public long delete(String table_name, String condition, String[] Param) {
		return db.delete(table_name, condition, Param);
	}

	public long insert(String table_name, String[] fields, String[] values) {
		ContentValues contentValues = new ContentValues();
		for (int i = 0; i < fields.length; i++) {
			contentValues.put(fields[i], values[i]);
		}

		return db.insert(table_name, null, contentValues);
	}

	public long update(String table_name, String[] fields, String[] values,
			String condition, String[] Param) {
		ContentValues contentValues = new ContentValues();
		for (int i = 0; i < fields.length; i++) {
			contentValues.put(fields[i], values[i]);
		}

		return db.update(table_name, contentValues, condition, Param);
	}

}
