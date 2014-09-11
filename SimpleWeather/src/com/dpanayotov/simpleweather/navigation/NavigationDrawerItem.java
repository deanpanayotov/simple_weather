package com.dpanayotov.simpleweather.navigation;

public class NavigationDrawerItem {
	private int mImageResourceId;
	private int mTextResourceId;

	public int getImageResourceId() {
		return mImageResourceId;
	}

	public void setImageResourceId(int imageResourceId) {
		this.mImageResourceId = imageResourceId;
	}

	public int getTextResourceId() {
		return mTextResourceId;
	}

	public void setTextResourceId(int textResourceId) {
		this.mTextResourceId = textResourceId;
	}

	public NavigationDrawerItem(int imageResourceId, int textResourceId) {
		super();
		this.mImageResourceId = imageResourceId;
		this.mTextResourceId = textResourceId;
	}

	@Override
	public String toString() {
		return "NavigationDrawerItem [imageResourceId=" + mImageResourceId
				+ ", textResourceId=" + mTextResourceId + "]";
	}

}
