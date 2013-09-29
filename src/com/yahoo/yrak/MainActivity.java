package com.yahoo.yrak;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button done_rak;
	Button new_random_rak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		setContentView(R.layout.activity_main);

		done_rak = (Button) findViewById(R.id.doneButton);
		done_rak.setOnClickListener(done_listener);
		
		new_random_rak = (Button) findViewById(R.id.newButton);
		new_random_rak.setOnClickListener(new_rak_listener);
		

	}

	private OnClickListener done_listener = new OnClickListener(){
    	@Override
    	public void onClick(View v) {
    		//Do Something
    		
    	}
	};
	
	private OnClickListener new_rak_listener = new OnClickListener(){
    	@Override
    	public void onClick(View v) {
    		//Do Something
    		
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
