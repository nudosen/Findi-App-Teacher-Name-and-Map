package com.example.nsisong;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.nsisong.R;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.nsisong.MESSAGE";

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) 
	{
		//Intent intent = new Intent(this,DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		//SharedPreferences sharedPref =getPreferences(Context.MODE_PRIVATE);
		//SharedPreferences.Editor editor = sharedPref.edit();
		String foundRoom=FindTeacherNumber(message);
	
	
	int RoomNumber = Integer.valueOf(foundRoom); 
	
	Intent floorIntent = null;
	
	if ( RoomNumber < 4000 && RoomNumber > 3000)	
	{
		floorIntent = new Intent(this, Third.class);
	}
	else if (RoomNumber < 3000 && RoomNumber > 2000)
	{
		floorIntent = new Intent(this, SecondFloor.class);
	}
	else if (RoomNumber < 2000 && RoomNumber > 1000)
	{ 
		 floorIntent = new Intent(this, FirstFloor.class);
		
	}
	if (floorIntent == null)
	{ 
		editText.setError("Error! Teacher not found."); //error
	}
	else
	{
		 floorIntent.putExtra("RoomNumber", "Room Number: " + foundRoom);
		startActivity(floorIntent);
		
	}
}

	
public String FindTeacherNumber(String teacher){
	String message= "0";
try {
	InputStream inputStream = getResources().openRawResource(R.raw.database);
	BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
	String line;
	String delims = ";";
	while((line=buf.readLine())!= null)
	{
		String[] tokens = line.split(delims);
		if (tokens[0].toLowerCase().equals(teacher.toLowerCase()))
		{		
			message= tokens[1];
			break;
		}
	}
                    
} catch(IOException e) {
             
}
return message;
}
TextView mTextView; // Member variable for text view in the layout
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the user interface layout for this Activity
	    // The layout file is defined in the project res/layout/main_activity.xml file
	    setContentView(R.layout.activity_main);
	    
	    // Initialize member TextView so we can manipulate it later
	    mTextView = (TextView) findViewById(R.id.edit_message);
	    
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    
	    // Stop method tracing that the activity started during onCreate()
	    android.os.Debug.stopMethodTracing();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
