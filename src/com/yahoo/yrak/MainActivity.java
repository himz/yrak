package com.yahoo.yrak;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class MainActivity extends Activity {
	Button done_rak;
	private RAKItem raki;
	private TextView random_rak;
	private RAKItem curr_RAK;

	static double s_lat = 0.0;
	static double s_long = 0.0;
	protected static String search_country;

	 private ShakeListener mShaker;
	 
	//variables to get current location
	LocationManager LC;
	private String provider; 
	LinearLayout mainlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		setContentView(R.layout.activity_main);

		mainlayout = (LinearLayout) View.inflate(this, R.layout.activity_main, null);

		Parse.initialize(this, "VcWFMlpjiVbfyHjlRMENluX6TRF7a89WQAVwR2Ui",
				"mJKFROuyFQVmxvgVNCh8dCEKJ8rhQ072jjpvDegX"); 

		ParseAnalytics.trackAppOpened(getIntent());

		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();

		done_rak = (Button) findViewById(R.id.doneButton);
		done_rak.setOnClickListener(done_listener);

		random_rak = (TextView) findViewById(R.id.textView1);

		//get location details:
		LC =(LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria=new Criteria();
		provider=LC.getBestProvider(criteria, false);
		Location loc=LC.getLastKnownLocation(provider);
		if (loc!=null){
			onLocationChanged(loc);
		}
		else{
			Log.d("LOCATION: ", "No Provider");
		}

		System.out.println("LAT: "+s_lat + "LONG: "+ s_long);


		final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		mShaker = new ShakeListener(this);
		mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
			public void onShake()
			{
				System.out.println("SHOOK!!!");

				
				curr_RAK =  DBHelper.getRandomRAK(search_country);
				if (curr_RAK!=null){
					String rak = curr_RAK.getmTodo();
					System.out.println("rak item: " +rak);
					random_rak.setText(rak);
				}

			}
		});
	}

	@Override
	public void onResume()
	{
		mShaker.resume();
		super.onResume();
	}
	@Override
	public void onPause()
	{
		mShaker.pause();
		super.onPause();
	}




	public void onLocationChanged(Location location) {
		double lat=(double) (location.getLatitude());
		double lon=(double) (location.getLongitude());

		s_lat = lat;
		s_long = lon;

	}

	public void onProviderDisabled(String provider) {
		Log.d("LOCATION: ", "provider disabled");

	}

	public void onProviderEnabled(String provider) {
		Log.d("LOCATION: ", "provider enabled");

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	private OnClickListener done_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			//Do Something

			//			ParseObject rakObject = new ParseObject("RAKObject");
			//
			//			String s = "testing";
			//			rakObject.put("todo", s);
			//			rakObject.put("duration", s);
			//			rakObject.put("lat_location", s);
			//			rakObject.put("long_location", s);
			//			rakObject.put("tag", s);
			//			rakObject.put("msg", s);
			//			
			//			rakObject.saveInBackground();
			String msg = curr_RAK.getmMessage();
			Context context = getApplicationContext();
			CharSequence text = msg;
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

		}
	};






	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.login, menu);
		getMenuInflater().inflate(R.menu.action_bar, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//		if (item.getItemId() == R.id.timeline)
		//		{
		//			Intent intent = new Intent(this, TimelineActivity.class);
		//			startActivity(intent);
		//		} else 
		if (item.getItemId() == R.id.profile)
		{
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		}//else if (item.getItemId() == R.id.settings)
		//		{
		//			Intent intent = new Intent(this, SettingsActivity.class);
		//			startActivity(intent);
		//		}

		//TODO: Add Settings activity piece
		//TODO: CHoose correct drawables in action_bar in res/menu
		return true;
	}
}
