package com.example.nsisong;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.nsisong.MESSAGE";

	private Spinner spinner2;
	private Button btnSubmit;
	
	public String selectedTeacher;
	
	//** Called when the user clicks the Send button */
	public void sendMessage(View view)
	{
		//Intent intent = new Intent(this,DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String teacherName = editText.getText().toString();
		//SharedPreferences sharedPref =getPreferences(Context.MODE_PRIVATE);
		//SharedPreferences.Editor editor = sharedPref.edit();
		String[] teacherInformation=FindTeacherNumber(teacherName);
	
		String RoomNumberString=teacherInformation[0];
		String CoordinateX=teacherInformation[1];
		String CoordinateY= teacherInformation[2];

		int RoomNumber= Integer.parseInt(teacherInformation[0]);
		//Use integer version to figure out floor, string used to stores extra
	
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
			 floorIntent.putExtra("RoomNumber", "Room Number: " + RoomNumberString);
			 floorIntent.putExtra("CoordinateX", CoordinateX);
			 floorIntent.putExtra("CoordinateY", CoordinateY);
			 floorIntent.putExtra("teacherName", "Teacher Name:" + teacherName);
			 startActivity(floorIntent);
		}
	}

	public String[] FindTeacherNumber(String teacher){
		String RoomNumber= "0";
		String CoordinateX ="-1";
		String CoordinateY = "-1";
		
		if (teacher.isEmpty() == false)
		{
			try {
				InputStream inputStream = getResources().openRawResource(R.raw.database);
				BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				String delims = ";";
				while((line=buf.readLine())!= null)
				{
					String[] tokens = line.split(delims);
					
					if ((tokens.length == 4) &&
							tokens[0].toLowerCase().equals(teacher.toLowerCase()))
					{		
						RoomNumber= tokens[1];
						CoordinateX= tokens[2];
						CoordinateY= tokens[3];
						//CoordinateX= Integer.parseInt(tokens[2]);
						//CoordinateY= Integer.parseInt(tokens[3]);
						
						break;
					}
				}
			                    
			} catch(IOException e) {
			             
			}
		}
		String[] teacherInformation = {RoomNumber, CoordinateX, CoordinateY};
		return teacherInformation;
	}
	
	public LinkedList<TeacherInformation> readDatabase()
	{
		LinkedList<TeacherInformation> list = new LinkedList();
		try {
		InputStream inputStream = getResources().openRawResource(R.raw.database);
		BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		String delims = ";";
		while((line=buf.readLine())!= null)
		{
			String[] tokens = line.split(delims);
			
			if (tokens.length == 4)
			{ 
				list.add(new TeacherInformation("", tokens[0], tokens[1], tokens[2], tokens[3]));
				}
		
		}
		}catch(IOException e) {
	        
		}
		return list;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the user interface layout for this Activity
	    // The layout file is defined in the project res/layout/main_activity.xml file
	    setContentView(R.layout.activity_main);
	    
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();
	    
	    LinkedList<TeacherInformation> list = readDatabase();
	    
	    Iterator<TeacherInformation> itr = list.iterator();
	    List<String> TILN = new ArrayList<String>();
	    while (itr.hasNext()) {
	    	TILN.add(itr.next().LastName);
	    }
	    Collections.sort(TILN);
	    spinner2= (Spinner)findViewById(R.id.spinner2);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,TILN);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner2.setAdapter(adapter);	
	    //spinner2.setPrompt("or select a teacher's name or building location");
	}
	
	public void addListenerOnButton() {
			
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
	 
		btnSubmit.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	 /*
		    Toast.makeText(MainActivity.this,
			"OnClickListener : " + 
	                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
				Toast.LENGTH_SHORT).show();
		    */
			// two lines got the text out of the spinner into the send message thing
			  EditText editText = (EditText) findViewById(R.id.edit_message);
			  editText.setText(String.valueOf(spinner2.getSelectedItem()));
			  MainActivity.this.selectedTeacher = String.valueOf(spinner2.getSelectedItem());
			  MainActivity.this.sendMessage(v);
		  }
	 
		});
	}
	
	public void addListenerOnSpinnerItemSelection() {
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
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
