package com.yahoo.yrak;

public class RAKItem {
	private String mTodo;
	private String mDuration;
	private String mLat_location;
	private String mLong_location;
	private String mTag; //for now only 1
	private String mMessage;
	
	public RAKItem(String todo, String duration, String lat_loc, String long_loc, String tag, String msg){
		this.mTodo = todo;
		this.mDuration = duration;
		this.mLat_location = lat_loc;
		this.mLong_location = long_loc;
		this.mTag = tag;
		this.mMessage = msg;
	}
	
	public String getmTodo() {
		return mTodo;
	}

	public void setmTodo(String mTodo) {
		this.mTodo = mTodo;
	}

	public String getmDuration() {
		return mDuration;
	}

	public void setmDuration(String mDuration) {
		this.mDuration = mDuration;
	}

	public String getmLat_location() {
		return mLat_location;
	}

	public void setmLat_location(String mLat_location) {
		this.mLat_location = mLat_location;
	}

	public String getmLong_location() {
		return mLong_location;
	}

	public void setmLong_location(String mLong_location) {
		this.mLong_location = mLong_location;
	}

	public String getmTag() {
		return mTag;
	}

	public void setmTag(String mTag) {
		this.mTag = mTag;
	}

	public String getmMessage() {
		return mMessage;
	}

	public void setmMessage(String mMessage) {
		this.mMessage = mMessage;
	}



}
