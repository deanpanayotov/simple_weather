package com.dpanayotov.simpleweather.db;

import com.dpanayotov.simpleweather.util.LogUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.HandlerThread;

public class Cache {

	private SQLiteDatabase database;
	private CacheSQLiteHelper dbHelper;
	private long mInvalidatePeriod;
	private String[] mColumns = new String[] { CacheContract.Responses.COLUMN_RAW_JSON };
	private long mLastInsert = 0;
	private static final String HANDLER_CACHE_THREAD_NAME = "cache";
	private HandlerThread mWorkerThread;
	private Handler mHandler;
	private boolean mIsInvalidateTaskScheduled = false;

	private Runnable mInvalidateCache = new Runnable() {

		@Override
		public void run() {
			if (mLastInsert > (System.currentTimeMillis() - mInvalidatePeriod)) {
				scheduleInvalidateTask();
			} else {
				mIsInvalidateTaskScheduled = false;
				LogUtil.d(LogUtil.CACHE_TAG, "Invalidate task dismissed");
				printAllResponses();
			}
			invalidate();
		}
	};

	private void scheduleInvalidateTask() {
		LogUtil.d(LogUtil.CACHE_TAG, "Scheduling new cleanup task");
		printAllResponses();
		mHandler.postDelayed(mInvalidateCache, mInvalidatePeriod);
		mIsInvalidateTaskScheduled = true;
	}

	public Cache(long invalidatePeriod, Context context) {
		dbHelper = new CacheSQLiteHelper(context);
		mInvalidatePeriod = invalidatePeriod;
		mWorkerThread = new HandlerThread(HANDLER_CACHE_THREAD_NAME);
		mWorkerThread.start();
		mHandler = new Handler(mWorkerThread.getLooper());
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * 
	 * @param location
	 * @param timestamp
	 * @param json
	 * @return true if the insert operation is completed successfuly; false
	 *         otherwise
	 */
	public boolean insertResponse(String location, long timestamp, String json) {
		deleteResponse(location);
		ContentValues values = new ContentValues();
		values.put(CacheContract.Responses.COLUMN_LOCATION, location);
		values.put(CacheContract.Responses.COLUMN_TIMESTAMP, timestamp);
		values.put(CacheContract.Responses.COLUMN_RAW_JSON, json);
		if (database.insert(CacheContract.Responses.TABLE_NAME, null, values) != -1) {
			mLastInsert = System.currentTimeMillis();
			if (mIsInvalidateTaskScheduled) {
				scheduleInvalidateTask();
			}
			LogUtil.d(LogUtil.CACHE_TAG, "A response was cached!");
			printAllResponses();
			return true;
		}
		LogUtil.d(LogUtil.CACHE_TAG, "A response failed to be cached!");
		printAllResponses();
		return false;
	}

	public void deleteResponse(String location) {
		database.delete(CacheContract.Responses.TABLE_NAME,
				CacheContract.Responses.COLUMN_LOCATION + " = "
						+ CacheContract.APOSTROPHE + location
						+ CacheContract.APOSTROPHE, null);
		LogUtil.d(LogUtil.CACHE_TAG, "A response was deleted!");
		printAllResponses();
	}

	public void invalidate() {
		database.delete(
				CacheContract.Responses.TABLE_NAME,
				CacheContract.Responses.COLUMN_TIMESTAMP + " < "
						+ (System.currentTimeMillis() - mInvalidatePeriod),
				null);
		LogUtil.d(LogUtil.CACHE_TAG, "Database invalidated!");
		printAllResponses();
	}

	public String getResponse(String location) {
		Cursor c = database.query(CacheContract.Responses.TABLE_NAME, mColumns,
				selection(location), null, null, null, null);
		if (!c.moveToFirst()) {
			LogUtil.d(LogUtil.CACHE_TAG, "Response not aquired!");
			printAllResponses();
			return null;
		}
		if (c.isAfterLast()) {
			LogUtil.d(LogUtil.CACHE_TAG, "Response not aquired!");
			printAllResponses();
			return null;
		}
		LogUtil.d(LogUtil.CACHE_TAG, "Response aquired!");
		printAllResponses();
		return c.getString(0);
	}

	private String selection(String location) {
		StringBuilder sb = new StringBuilder();
		sb.append(CacheContract.Responses.COLUMN_LOCATION);
		sb.append(CacheContract.EQUALS);
		sb.append(CacheContract.APOSTROPHE);
		sb.append(location);
		sb.append(CacheContract.APOSTROPHE);
		sb.append(CacheContract.AND);
		sb.append(CacheContract.Responses.COLUMN_TIMESTAMP);
		sb.append(CacheContract.BIGGER_THAN);
		sb.append(System.currentTimeMillis() - mInvalidatePeriod);
		return sb.toString();
	}

	public void printAllResponses() {
		if (LogUtil.CACHE_DEBUG_ENABLED) {
			Cursor c = database.query(CacheContract.Responses.TABLE_NAME,
					CacheContract.Responses.ALL_COLUMNS, null, null, null,
					null, null, null);
			c.moveToFirst();
			LogUtil.d(LogUtil.CACHE_TAG,
					"Dumping everything in table \"responses\"");
			LogUtil.d(LogUtil.CACHE_TAG,
					"--------------------------------------");
			while (!c.isAfterLast()) {
				LogUtil.d(
						LogUtil.CACHE_TAG,
						c.getInt(0) + " | " + c.getString(1) + " | "
								+ c.getString(2));
				c.moveToNext();
			}
			LogUtil.d(LogUtil.CACHE_TAG,
					"--------------------------------------");
		}
	}
}
