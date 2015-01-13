package com.example.nsisong;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ThirdFloor extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	    getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_third_floor);
		
		Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(b);
		
		String newString;
		if (savedInstanceState == null) {
			   Bundle extras = getIntent().getExtras();
			    if(extras == null) {
			        newString= null;
			    } else {
			        newString= extras.getString("RoomNumber");
			    }
			} else {
			    newString= (String) savedInstanceState.getSerializable("RoomNumber");
			}
			
			TextView number = (TextView) findViewById(R.id.textView1);
			number.setText(newString);
			
	}

	
	protected void onDraw (Canvas canvas)
	{
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.layout.activity_third_floor);
		canvas.drawBitmap(bmp, 50, 50, null);
		TextView number = (TextView) findViewById(R.id.textView1);
		number.setText("Hi Charles");
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third_floor, menu);
		return true;
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
		if (id == R.id.home) {
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
