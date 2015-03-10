package com.example.nsisong;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class FirstFloor extends ActionBarActivity


{
	DrawView drawing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	    getActionBar().setHomeButtonEnabled(true);
		//setContentView(R.layout.activity_third);
		setContentView( new DrawView(this));  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_floor, menu);
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

	private class DrawView extends View {

        public DrawView(Context context) {
            super(context);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
        	super.onDraw(canvas);
        	int x = getWidth();
        	int y = getHeight();
        	int radius;
        	radius = 3;
        	Paint paint = new Paint();
        	paint.setColor(Color.GREEN);
        	
        	
        	
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.firstfloor1);
            //replace third with first and second respectively
    		int FirstFloorPicturex= bmp.getHeight();
    		int FirstFloorPicturey= bmp.getWidth();
    		Rect src = new Rect();
    		Rect dst = new Rect();
    		
    		dst.right= x;
    		dst.bottom= y+60;
    		dst.left= 0;
    		dst.top= 0;
    		
    		src.right= FirstFloorPicturex;
    		src.bottom=FirstFloorPicturey;
    		src.left=0;
    		src.top=0;
    		

    		
    		canvas.drawBitmap(bmp, src, dst, null);
    		
    		canvas.drawCircle(114, 120, radius, paint);

    		String string = Integer.toString(FirstFloorPicturex) + ", " + Integer.toString(FirstFloorPicturey);
    		paint.setTextSize(20);
    		canvas.drawText(string, 20, 20, paint);    		
    		
    		
   
    		
        }
	}



}