package com.dpanayotov.simpleweather.general;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class LRUBitmapCache extends LruCache<String, Bitmap> implements
		ImageCache {

	private final static double CACHE_RATIO = 0.125;
	private final static int K = 1024;

	public static int getDefaultLruCacheSize() {
		return (int) ((Runtime.getRuntime().maxMemory() / K) * CACHE_RATIO);
	}

	public LRUBitmapCache() {
		this(getDefaultLruCacheSize());
	}

	public LRUBitmapCache(int sizeInKiloBytes) {
		super(sizeInKiloBytes);
	}

	@Override
	protected int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight() / 1024;
	}

	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}
}
