package com.dpanayotov.simpleweather.db;

import android.provider.BaseColumns;

public class CacheContract {
	public CacheContract() {
	}
	
	public static final String TYPE_TEXT = " TEXT";
	public static final String TYPE_INTEGER = " INTEGER";
	public static final String PRIMARY_KEY = " PRIMARY KEY";
	public static final String AUTOINCREMENT = " AUTOINCREMENT";
	public static final String NOT_NULL = " NOT NULL";

	public static final String CREATE_TABLE = " CREATE TABLE ";

	public static final String AND = " AND ";
	public static final String COMMA = ", ";
	public static final String OPEN_BRACKET = " ( ";
	public static final String CLOSE_BRACKET = ")";
	public static final String SEMICOLON = ";";
	public static final String QUOTE = "\"";
	public static final String EQUALS = " = ";
	public static final String SMALLER_THAN = " < ";
	public static final String BIGGER_THAN = " > ";
	public static abstract class Responses implements BaseColumns {
		public static final String TABLE_NAME = "responses";
		public static final String COLUMN_LOCATION = "location";
		public static final String COLUMN_TIMESTAMP = "timestamp";
		public static final String COLUMN_RAW_JSON = "raw_json";
	}
}