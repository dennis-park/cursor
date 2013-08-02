package com.example.pointerapp;

//import junit.framework.Assert;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
	//	private final float NOISE = (float) 2.0;
	//
	//	private GestureLibrary mLib;
	private SensorManager mManager;
	private Sensor accelSensor, gyroSensor;
	private MousePointerView pointerView;
	// accelerometer and magnetometer based rotation matrix
	private float[] rotationMatrix = new float[9];
	// orientation angles from accel and magnet
	private float[] accMagOrientation = new float[3];
	private float[] magnet = new float[3];
	private float[] accel = new float[3];
	private float[] gyro = new float[3];
	private float[] grav = new float[3];
	private float[] vel = new float[3];
	private float[] ivel = new float[3];
	private float[] disp = new float[3];
	private float[] idisp = new float[3];
	private boolean FLAG_INIT = false;
	private float mTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//display finger cursor on single canvas
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), 
				R.drawable.cursor);
		mManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		accelSensor = mManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		gyroSensor = mManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		initListeners();

		SensorManager.getRotationMatrix(rotationMatrix, accMagOrientation, grav, magnet);
		//pass accel and magnetic
		SensorManager.getOrientation(rotationMatrix, accMagOrientation);
		
		pointerView = new MousePointerView(this, bitmap);
		setContentView(pointerView);

	}
	private void initListeners() {
		mManager.registerListener(this,accelSensor,mManager.SENSOR_DELAY_NORMAL);
		mManager.registerListener(this,gyroSensor,SensorManager.SENSOR_DELAY_FASTEST);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		mManager.unregisterListener(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		mManager.registerListener(this,
				mManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onPause() {
		super.onPause();
		if (accelSensor != null || gyroSensor != null) {
			mManager.unregisterListener(this);
		}
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// flag for initialization
		int mType = event.sensor.getType();
		switch (mType) {
			case Sensor.TYPE_LINEAR_ACCELERATION:
				if (!FLAG_INIT) {
					FLAG_INIT = true;
					mTime = 0.0f;
				} else {
					integrate(event);
				}	
				//redraw position of cursor change
				pointerView.updatePosition(disp);
				break;
			case Sensor.TYPE_GYROSCOPE:
				//process gyro data
				filter(gyro[0], gyro[1]);
				break;
		}
	}
	public void calculateAccMagOrientation() {
		if(SensorManager.getRotationMatrix(rotationMatrix, null, accel, magnet)) {
			SensorManager.getOrientation(rotationMatrix, accMagOrientation);
		}
	}
	float tt;
	double GYROSCOPE_SENSITIVITY = 65.536;
	double ACCELEROMETER_SENSITIVITY = 8192.0;
	
	public void filter(float pitch, float roll) {
		float pitchAccel, rollAccel;
		
		//integrate gyro
		pitch += (gyro[0] / GYROSCOPE_SENSITIVITY) * tt;
		roll  -= (gyro[1] / GYROSCOPE_SENSITIVITY) * tt;
		
		int sens = 32768;
		
		int forceMagnitudeApprox = (int) (Math.abs(accel[0]) + Math.abs(accel[1]) + Math.abs(accel[2]));
		if (forceMagnitudeApprox > 8192 && forceMagnitudeApprox < sens) {
			// Turning around the X axis results in a vector on the Y-axis
			pitchAccel = (float) (Math.atan2(accel[1], accel[2]) * 180 / Math.PI);
			//complementary eq
			pitch = (float) (pitch * 0.98 + pitchAccel * 0.02);
			
			// Turning around the Y axis results in a vector on the X-axis
			rollAccel = (float) (Math.atan2(accel[0], accel[2]) * 180 / Math.PI);
			roll = (float) (roll * 0.98 + rollAccel * 0.02);
		}
	}

	public void integrate(SensorEvent event) {
		float dt = (event.timestamp - mTime)/1000000000.0f;
		mTime = event.timestamp;
		for(int i = 0; i < 3; i++){
			accel[i] += event.values[i];
			vel[i] = ivel[i] + accel[i] * dt;
			ivel[i] = vel[i];
			disp[i] += idisp[i] + vel[i] * dt;
			idisp[i] = disp[i];
		}
		tt = dt;
	} 
}

