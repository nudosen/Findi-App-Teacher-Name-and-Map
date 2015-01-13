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
	Intent intent = new Intent(this,DisplayMessageActivity.class);
	EditText editText = (EditText) findViewById(R.id.edit_message);
	String message = editText.getText().toString();
	SharedPreferences sharedPref =getPreferences(Context.MODE_PRIVATE);
	SharedPreferences.Editor editor = sharedPref.edit();
message=FindTeacherNumber(message);


float a = (Integer.valueOf(message)).floatValue(); 

//if (a >= 4000){
	//System.out.println("An invalid room number has been entered. Please try again.");
//}



if ( a < 4000 && a > 3000)	
{
	 intent = new Intent(this, ThirdFloor.class);
}
else if (a < 3000 && a > 2000)
{
	intent = new Intent(this, SecondFloor.class);
}
else
{ 
	intent = new Intent(this, FirstFloor.class);
}
	editor.putString ("RoomNumber", message);
	editor.commit();
	intent.putExtra("RoomNumber", message);
    startActivity(intent);
	LoadMap();
	
}
public void LoadMap(){
	/*File imgFile = new  File("./first.floor.school.png");

	if(imgFile.exists()){

	    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

	    //ImageView myImage = (ImageView) findViewById(R.);
	    Intent intent = new Intent(this, ImageView.class);
	    intent.putExtra(EXTRA_MESSAGE, myBitmap);
	    startActivity(intent);

	    //myImage.setImageBitmap(myBitmap);

	}
	else {
		Intent intent = new Intent(this, ThirdFloor.class);
		intent.putExtra (EXTRA_MESSAGE, "Error!");
		startActivity(intent);
	
	}*/
	//ImageView imageView = new ImageView(getApplicationContext());
	//LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	//String path =  "/Nsisong/bin/res/crunch/drawable-mdpi/first.floor.school.png";
	//Bitmap image = BitmapFactory.decodeFile(path);
	//imageView.setImageBitmap(image);
	//RelativeLayout rl = (RelativeLayout) findViewById(R.id.image);
	//rl.addView(imageView, lp);
}
	
	
public String FindTeacherNumber(String teacher){
	String message= "0";
try {
	InputStream inputStream = getResources().openRawResource(R.raw.database);
	BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
	String line;
	while((line=buf.readLine())!= null)
	{
		if (line.contains(teacher))
		{
			String delims = ";";
			String[] tokens = line.split(delims);
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
