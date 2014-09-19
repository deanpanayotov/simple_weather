package com.dpanayotov.simpleweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.HandlerThread;

import com.dpanayotov.simpleweather.util.LogUtil;

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
				debug("A new invalidate task was NOT scheduled - db is empty.");
			}
			invalidate();
		}
	};

	private void scheduleInvalidateTask() {
		debug("Scheduling new invalidate task.");
		mHandler.postDelayed(mInvalidateCache, mInvalidatePeriod);
		mIsInvalidateTaskScheduled = true;
	}

	public Cache(long invalidatePeriod, Context context) {
		dbHelper = new CacheSQLiteHelper(context);
		database = dbHelper.getWritableDatabase();
		mInvalidatePeriod = invalidatePeriod;
		mWorkerThread = new HandlerThread(HANDLER_CACHE_THREAD_NAME);
		mWorkerThread.start();
		mHandler = new Handler(mWorkerThread.getLooper());
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
			if (!mIsInvalidateTaskScheduled) {
				scheduleInvalidateTask();
			}
			debug("A response was cached!");
			return true;
		}
		debug("A response failed to be cached!");
		return false;
	}

	public void deleteResponse(String location) {
		database.delete(CacheContract.Responses.TABLE_NAME,
				CacheContract.Responses.COLUMN_LOCATION + " = "
						+ CacheContract.APOSTROPHE + location
						+ CacheContract.APOSTROPHE, null);
		debug("A response was deleted!");
	}

	public void invalidate() {
		database.delete(
				CacheContract.Responses.TABLE_NAME,
				CacheContract.Responses.COLUMN_TIMESTAMP + " < "
						+ (System.currentTimeMillis() - mInvalidatePeriod),
				null);
		debug("Database invalidated!");
	}

	public String getResponse(String location) {
		Cursor c = database.query(CacheContract.Responses.TABLE_NAME, mColumns,
				selection(location), null, null, null, null);
		if (!c.moveToFirst()) {
			debug("Response NOT aquired from cache!");
			return null;
		}
		if (c.isAfterLast()) {
			debug("Response NOT aquired from cache!");
			return null;
		}
		debug("Response aquired from cache!");
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
	
	public void debug(String message){
		if(LogUtil.CACHE_DEBUG_ENABLED){
			LogUtil.d(LogUtil.CACHE_TAG, message);
			printAllResponses();
		}
	}
	
}
