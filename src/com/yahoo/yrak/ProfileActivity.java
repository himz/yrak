package com.yahoo.yrak;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileActivity extends Activity{

	Button change_loc_button;
	Button yahoo_login;
	TextView curr_loc;

	private  ArrayList<HashMap<String, String>> itemsList;
	private ArrayList<String> addedNames;

	static double s_lat = MainActivity.s_lat;
	static double s_long = MainActivity.s_long;
	public static String search_country;
	private Context cxt;

	//XML node keys
	static final String KEY_NAME = "name";
	static final String KEY_PRICE = "price";//float
	static final String KEY_STATUS = "status";//int
	static final String KEY_DESC = "description";
	static final String KEY_CATEGORY = "category";
	static final String KEY_CONDITION = "condition";
	static final String KEY_STARTTIME = "start-time";
	static final String KEY_HIGHESTPRICE= "max-bid";
	static final String KEY_LOCATION = "location";
	static final String KEY_SELLERNAME = "seller_id"; //int
	static final String KEY_EXPIREDATE = "end-time"; //datetime
	static final String KEY_THUMB_URL= "picture-url";

	Spinner loc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		setContentView(R.layout.activity_profile);

		cxt = this;
		//	    # Just for testing, allow network access in the main thread
		//	    # NEVER use this is productive code
		StrictMode.ThreadPolicy policy = new StrictMode.
				ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 


		curr_loc = (TextView) findViewById(R.id.textView1);
		setCurrentLocation();

		change_loc_button = (Button) findViewById(R.id.change_my_loc_button);
		change_loc_button.setOnClickListener(change_loc_listener);

		yahoo_login = (Button) findViewById(R.id.yahoo_login);
		yahoo_login.setOnClickListener(yahoo_login_listener);

	}

	private void setCurrentLocation() {
		//String URL = "http://"+localURL+":3000/api/users/"+userNum+"/watch_list.xml";
//		String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";

		//Query to insert "example text content"
//		String query = "select%20*%20from%20geo.placefinder%20WHERE%20longitude%20%3D%20%22+" + s_long + "%22%20AND%20latitude%3D%22"+ s_lat+"%22&format=json&diagnostics=true&callback=";	 
//		String fullStr = baseUrl+query;

//		String fullStr = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20geo.placefinder%20WHERE%20text%3D%2255.948496%2C-3.198909%22%20AND%20gflags%3D%22R%22&format=xml&diagnostics=true&callback=";
		
		String fullStr = "http://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20geo.placefinder%20WHERE%20text%3D%22"+ s_lat + "%2C"+s_long+"%22%20AND%20gflags%3D%22R%22&format=xml&diagnostics=true&callback=";

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(fullStr); // getting XML from URL
		System.out.println("full string: "+fullStr);
		Log.d("TAG", xml);
		//		Document doc = parser.getDomElement(xml); // getting DOM element
		//
		//		NodeList nl = doc.getElementsByTagName("query/results/Result");
		//		// looping through all song nodes &lt;song&gt;
		//		for (int i = 0; i < nl.getLength(); i++) {
		//			// creating new HashMap
		//			HashMap<String, String> map = new HashMap<String, String>();
		//			Element e = (Element) nl.item(i);

		//			String newName = parser.getValue(e, KEY_NAME);
		//			if (!addedNames.contains(newName)){
		//				addedNames.add(newName);

		// adding each child node to HashMap key =&gt; value
		String city = null;
		String state = null;
		String country = null;


		city = xml.substring(xml.indexOf("<city>") + 6, xml.indexOf("</city>"));
		state = xml.substring(xml.indexOf("<state>") +7, xml.indexOf("</state>"));
		country= xml.substring(xml.indexOf("<country>") +9, xml.indexOf("</country>"));
		

		System.out.println( city + ", " + state + ", " + country);
		curr_loc.setText(curr_loc.getText() + " " + state + ", " + country);
		
		//				map.put("city", city);
		//				map.put("state", state);
		//				map.put("country", country);
		//				 

		//				map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
		//				map.put(KEY_CATEGORY, parser.getValue(e, KEY_CATEGORY));
		//				map.put(KEY_CONDITION, parser.getValue(e, KEY_CONDITION));
		//				map.put(KEY_STARTTIME, parser.getValue(e, KEY_STARTTIME));
		//				map.put(KEY_LOCATION, parser.getValue(e, KEY_LOCATION));
		//				map.put(KEY_HIGHESTPRICE, parser.getValue(e, KEY_HIGHESTPRICE));
		//				map.put(KEY_SELLERNAME, parser.getValue(e, KEY_SELLERNAME));
		//				map.put(KEY_EXPIREDATE, parser.getValue(e, KEY_EXPIREDATE));
		//				map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));


		// adding HashList to ArrayList
		//				itemsList.add(map);
		//			}
	


}

private OnClickListener change_loc_listener = new OnClickListener(){
	@Override
	public void onClick(View v) {
		//Do Something

		
//		 System.out.println("SPINNER CHOSE: "+ change_loc_button.getSelectedItem());
				
		// create a Dialog component
		final Dialog dialog = new Dialog(cxt);

		//tell the Dialog to use the dialog.xml as it's layout description
		dialog.setContentView(R.layout.set_location);
		dialog.setTitle("Set my location");

		 loc = (Spinner) dialog.findViewById(R.id.change_location);


		Button dialogButton = (Button) dialog.findViewById(R.id.button1);

		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				search_country = loc.getSelectedItem().toString();
				System.out.println("SPINNER CHOSE: "+ loc.getSelectedItem());
				curr_loc.setText("Your current location is set to: " +search_country);
				MainActivity.search_country = search_country;
				dialog.dismiss();
			}
		});

		dialog.show();

	}
};

private OnClickListener yahoo_login_listener = new OnClickListener(){
	@Override
	public void onClick(View v) {
		//Do Something here Himanshu



	}
};
}
