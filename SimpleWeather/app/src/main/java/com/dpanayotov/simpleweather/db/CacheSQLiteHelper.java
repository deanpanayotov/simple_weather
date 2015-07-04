package com.dpanayotov.simpleweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dpanayotov.simpleweather.util.LogUtil;

public class CacheSQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "forecast_cache.db";
	private static final int DATABASE_VERSION = 1;

	private static final String databaseCreate() {
		StringBuilder sb = new StringBuilder();
		sb.append(CacheContract.CREATE_TABLE);
		sb.append(CacheContract.Responses.TABLE_NAME);
		sb.append(CacheContract.OPEN_BRACKET);
		sb.append(CacheContract.Responses._ID);
		sb.append(CacheContract.TYPE_INTEGER);
		sb.append(CacheContract.PRIMARY_KEY);
		sb.append(CacheContract.AUTOINCREMENT);
		sb.append(CacheContract.COMMA);
		// sb.append(" integer primary key autoincrement, ");
		sb.append(CacheContract.Responses.COLUMN_LOCATION);
		sb.append(CacheContract.TYPE_TEXT);
		sb.append(CacheContract.NOT_NULL);
		sb.append(CacheContract.COMMA);
		// sb.append(" text not null, ");
		sb.append(CacheContract.Responses.COLUMN_TIMESTAMP);
		sb.append(CacheContract.TYPE_INTEGER);
		sb.append(CacheContract.COMMA);
		// sb.append(" integer, ");
		sb.append(CacheContract.Responses.COLUMN_RAW_JSON);
		sb.append(CacheContract.TYPE_TEXT);
		sb.append(CacheContract.NOT_NULL);
		sb.append(CacheContract.CLOSE_BRACKET);
		sb.append(CacheContract.SEMICOLON);
		// sb.append(");");
		return sb.toString();
	}

	public CacheSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(databaseCreate());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		LogUtil.w(CacheSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + CacheContract.Responses.TABLE_NAME);
		onCreate(db);
	}
}
