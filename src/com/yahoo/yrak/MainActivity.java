package com.yahoo.yrak;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class MainActivity extends Activity {
	Button done_rak;
	Button new_random_rak;
	private RAKItem raki;
	private TextView random_rak;
	private RAKItem curr_RAK;

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
		
		random_rak = (TextView) findViewById(R.id.textView1);
		


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
			
			curr_RAK =  DBHelper.getRandomRAK();
			if (curr_RAK!=null){
				String rak = curr_RAK.getmTodo();
				System.out.println("rak item: " +rak);
				random_rak.setText(rak);
			}
			
			
			
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
