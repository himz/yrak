package com.yahoo.yrak;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class DBHelper
{

  public static ParseUser USER;
//  public static ArrayList<UserInfo> FRIENDSLIST = new ArrayList<UserInfo>();
//  static UserInfo friend;
//  private static DBHelper instance;

  private static RAKItem raki;
  
//  private DBHelper()
//  {
//    instance = this;
//  }
//
//  public static DBHelper getInstance()
//  {
//    if (instance == null)
//    {
//      synchronized (DBHelper.class)
//      {
//        if (instance == null)
//        {
//          instance = new DBHelper();
//        }
//      }
//    }
//    return instance;
//  }



	public static RAKItem getRAKItem(String id)
	  {
		
	    ParseQuery<ParseObject> rakQuery = ParseQuery.getQuery("RAKObject");
	    rakQuery.whereEqualTo("guid", id);

	    rakQuery.findInBackground(new FindCallback<ParseObject>()
	    {
	      public void done(List<ParseObject> results, ParseException e)
	      {
	        if (results != null)
	        {
	          String todo = results.get(0).getString("todo");
//	          String duration = results.get(0).getString("duration");
//	          String lat_location = results.get(0).getString("lat_location");
//	          String long_location = results.get(0).getString("long_location");
//	          String tag = results.get(0).getString("tag");
//	          String msg = results.get(0).getString("msg");
//	          raki = new RAKItem(todo, duration, lat_location, long_location, tag, msg);
	        }
	        System.out.println("results: " + results.get(0).getString("todo"));
	      }
	    });

	    return null;
	  }

}
