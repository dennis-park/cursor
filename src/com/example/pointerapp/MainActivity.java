package com.example.pointerapp;

//import junit.framework.Assert;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity implements SensorEventListener{
	private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private final float NOISE = (float) 2.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), 
				R.drawable.cursor);
		MousePointerView view = new MousePointerView(this, bitmap);
		setContentView(view);
	}
	//	public static int getDrawable(Context context, String name) {
	//		return context.getResources().getIdentifier(name, "drawable",
	//				context.getPackageName());
	//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		super.onResume();

	}
	@Override
	protected void onPause() {
		super.onPause();

	}



}
