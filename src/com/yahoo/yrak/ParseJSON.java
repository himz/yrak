package com.yahoo.yrak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

public class ParseJSON extends Activity {
	
	String jsonExecute;
	String jsonSelect;
	String jsonUpdate;
  
	static double s_lat = MainActivity.s_lat;
	static double s_long = MainActivity.s_long;
/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
//    # Just for testing, allow network access in the main thread
//    # NEVER use this is productive code
    StrictMode.ThreadPolicy policy = new StrictMode.
    ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy); 
    
    final TextView txt1 = (TextView) findViewById(R.id.textView1);

    setContentView(R.layout.activity_main);
    storeJsonFileOnServer();
    String readPlaceFinder = readPlaceFinder();
    try {
    	
   	 JSONTokener tok = new JSONTokener(readPlaceFinder);
   	 JSONObject result;
   	
   	 result = new JSONObject(tok);

   	 JSONObject query = result.getJSONObject("query");
   	 JSONObject results = query.getJSONObject("results");
   	JSONObject channel = results.getJSONObject("channel");
   	
   	JSONObject location = channel.getJSONObject("location");
   	
   	
   	
   	 //JSONObject item = results.getJSONObject("item");
   	// JSONObject channel = results.getJSONObject("channel");
   	 String test = location.getString("city");
   	 
   	 txt1.setText(test);
    	
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void storeJsonFileOnServer(){
	  StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	 //   http://query.yahooapis.com/v1/public/yql?q=insert%20into%20yql.storage.admin%20(value)%20values%20(%22example%20text%20content%22)%20&diagnostics=true  
	    String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";
		
	    //Query to insert "example text content"
	    String query = "select%20*%20from%20geo.placefinder%20WHERE%20longitude%20%3D%20%22+" + s_long + "%22%20AND%20latitude%3D%22"+ s_lat+"%22&format=json&diagnostics=true&callback=";	 
		String fullStr = baseUrl+query;
	    HttpPost httpGet = new HttpPost(fullStr);
	    try {
	      HttpResponse response = client.execute(httpGet);
	      StatusLine statusLine = response.getStatusLine();
	      int statusCode = statusLine.getStatusCode();
	      if (statusCode == 200) {
	        HttpEntity entity = response.getEntity();
	        InputStream content = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	//if(line.contains("<execute>")){
	        		//take the substring from store to the end. 
	        		//line.substring(line.indexOf("s"), line.indexOf("</execute"));
	        		
	        		
	        	//}
	        	builder.append(line);
	          
	        }
	      } else {
	        Log.e(ParseJSON.class.toString(), "Failed to download file");
	      }
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    //Builder has the complete xml file now. 
	    
  }
  
  
  public String readPlaceFinder() {
    StringBuilder builder = new StringBuilder();
    HttpClient client = new DefaultHttpClient();
//    String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";
//	 String query = "select%20*%20from%20weather.forecast%20where%20woeid%3D2502265&format=json&diagnostics=true";
//	 
//	 String fullStr = baseUrl+query;
    
    String query = "select%20*%20from%20geo.placefinder%20WHERE%20longitude%20%3D%20%22+" + s_long + "%22%20AND%20latitude%3D%22"+ s_lat+"%22&format=json&diagnostics=true&callback=";	 

    HttpGet httpGet = new HttpGet(query);
    try {
      HttpResponse response = client.execute(httpGet);
      StatusLine statusLine = response.getStatusLine();
      int statusCode = statusLine.getStatusCode();
      if (statusCode == 200) {
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        String line;
        while ((line = reader.readLine()) != null) {
          builder.append(line);
        }
      } else {
        Log.e(ParseJSON.class.toString(), "Failed to download file");
      }
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return builder.toString();
  }
} 