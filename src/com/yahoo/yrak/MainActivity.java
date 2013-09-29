package com.yahoo.yrak;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;

public class MainActivity extends Activity {
	Button done_rak;
	Button new_random_rak;
	private RAKItem raki;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		setContentView(R.layout.activity_main);

		Parse.initialize(this, "VcWFMlpjiVbfyHjlRMENluX6TRF7a89WQAVwR2Ui",
				"mJKFROuyFQVmxvgVNCh8dCEKJ8rhQ072jjpvDegX"); 

		ParseAnalytics.trackAppOpened(getIntent());

		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();

		done_rak = (Button) findViewById(R.id.doneButton);
		done_rak.setOnClickListener(done_listener);

		new_random_rak = (Button) findViewById(R.id.newButton);
		new_random_rak.setOnClickListener(new_rak_listener);


	}

	private OnClickListener done_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			//Do Something

			ParseObject rakObject = new ParseObject("RAKObject");

			String s = "testing";
			rakObject.put("todo", s);
			rakObject.put("duration", s);
			rakObject.put("lat_location", s);
			rakObject.put("long_location", s);
			rakObject.put("tag", s);
			rakObject.put("msg", s);
			
			rakObject.saveInBackground();
		}
	};

	private OnClickListener new_rak_listener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			//Do Something

			/*ParseQuery<ParseObject> query = ParseQuery.getQuery("RAKObject");
			 query.getInBackground("OQowgPEaWt", new GetCallback<ParseObject>() {
			 

					@Override
					public void done(ParseObject arg0,
							com.parse.ParseException arg1) {
						if (arg1 == null) {
							String pass = arg0.getString("todo");				    					     
							Toast toast = Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG);
							toast.show();
						}
						else{
							
						}
					}
			});*/
			
			DBHelper.getRAKItem("1234");
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
		if (item.getItemId() == R.id.timeline)
		{
			Intent intent = new Intent(this, TimelineActivity.class);
			startActivity(intent);
		} else if (item.getItemId() == R.id.profile)
		{
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		}else if (item.getItemId() == R.id.settings)
		{
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		}

		//TODO: Add Settings activity piece
		//TODO: CHoose correct drawables in action_bar in res/menu
		return true;
	}
}
