package com.example.nsisong;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class Third extends ActionBarActivity {
	DrawView drawing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
			
		}
		 //ViewGroup myLayout = (ViewGroup) findViewById(R.id.view1);

	        //drawing = new DrawView(this);
	        //myLayout.addView(drawing);  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
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
		return super.onOptionsItemSelected(item);
	}

	private class DrawView extends View {

        public DrawView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            Paint myPaint = new Paint();
            myPaint.setColor(Color.BLACK);
            //draw a circle
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.layout.activity_third_floor);
    		canvas.drawBitmap(bmp, 50, 50, null);
        }
    }
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() { 
			/*this.getView().invalidate();
		*/}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_third,
					container, false);
			return rootView;
		}
		
		protected void onDraw (Canvas canvas)
		{
			/*
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.layout.activity_third_floor);
			canvas.drawBitmap(bmp, 50, 50, null);
			*/
		}
	}
}
