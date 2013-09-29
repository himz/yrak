package com.yahoo.yrak;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity{

	Button change_loc_button;
	Button yahoo_login;
	TextView curr_loc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		setContentView(R.layout.activity_profile);
		
		curr_loc = (TextView) findViewById(R.id.textView1);
		setCurrentLocation();
		
		change_loc_button = (Button) findViewById(R.id.change_location);
		change_loc_button.setOnClickListener(change_loc_listener);
		
		yahoo_login = (Button) findViewById(R.id.yahoo_login);
		yahoo_login.setOnClickListener(yahoo_login_listener);
		
	}
	
	private void setCurrentLocation() {
		// TODO Auto-generated method stub
		
	}

	private OnClickListener change_loc_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			//Do Something

			

		}
	};
	
	private OnClickListener yahoo_login_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			//Do Something here Himanshu

			

		}
	};
}
