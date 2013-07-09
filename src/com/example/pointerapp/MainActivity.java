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

public class MainActivity extends Activity{
	//	private float mLastX, mLastY, mLastZ;
	//	private boolean mInitialized;
	//	private SensorManager mSensorManager;
	//	private Sensor mAccelerometer;
	//	private final float NOISE = (float) 2.0;
	//
	//	private GestureLibrary mLib;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//populate activity with appropriate data
		//		populate();

		//		//gesture library
		//		GestureOverlayView gestureView = new GestureOverlayView(this);
		//		View inflate = getLayoutInflater().inflate(R.layout.activity_main, null); //null Viewgroup
		//		gestureView.addView(inflate);
		//		gestureView.addOnGesturePerformedListener(this);
		//		mLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
		//		if (!mLib.load()) {
		//			finish();
		//		}
		//setContentView(gestureView);

		//display finger cursor on single canvas
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), 
				R.drawable.cursor);
		MousePointerView pointerView = new MousePointerView(this, bitmap);
		setContentView(pointerView);

		//		//fragment
		//		if (findViewById(R.id.fragment_container) != null) {
		//			if (savedInstanceState != null) {
		//				return;
		//			}
		//			Fragment frag = new Fragment();
		//			frag.setArguments(getIntent().getExtras());
		//			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		//			transaction.add(R.id.fragment_container, frag).commit();
		//		}
	}

	//	private void populate() {
	//		Cursor query = getContentResolver().query(ContactsContract.AUTHORITY_URI,
	//				new String[] {ContactsContract.PRIMARY_ACCOUNT_NAME}, null, null, null);
	//		
	//	}


	// add helper method to access and manip control to connect remotely
	//	@Override
	//	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
	//		ArrayList<Prediction> predictions = mLib.recognize(gesture);
	//		Log.v("performed", "performed");
	//		//at least one prediction
	//		if (predictions.size() > 0) {
	//			Prediction prediction = predictions.get(0);
	//			if (prediction.score > 1.0) {
	//				String action = predictions.get(0).name;
	//				if (action.equalsIgnoreCase("action_RIGHT")) {
	//					//test1
	//				} else if (action.equalsIgnoreCase("action_LEFT")) {
	//					//test2
	//				} else if (action.equalsIgnoreCase("action_REFRESH")) {
	//					//test3
	//				}
	//					
	//			}
	//		}
	//	}
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

	//	@Override
	//	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	//		// TODO Auto-generated method stub
	//
	//	}
	//	@Override
	//	public void onSensorChanged(SensorEvent event) {
	//		// TODO Auto-generated method stub
	//		TextView tvX = (TextView)findViewById(R.id.x_axis);
	//		TextView tvY = (TextView)findViewById(R.id.y_axis);
	//		TextView tvZ = (TextView)findViewById(R.id.z_axis);
	//		ImageView iv = (ImageView)findViewById(R.id.image);
	//		float x = event.values[0];
	//		float y = event.values[1];
	//		float z = event.values[2];
	//		if (!mInitialized) {
	//			mLastX = x;
	//			mLastY = y;
	//			mLastZ = z;
	//			tvX.setText("0.0");
	//			tvY.setText("0.0");
	//			tvZ.setText("0.0");
	//			mInitialized = true;
	//		} else {
	//			float deltaX = Math.abs(mLastX - x);
	//			float deltaY = Math.abs(mLastY - y);
	//			float deltaZ = Math.abs(mLastZ - z);
	//			if (deltaX < NOISE)
	//				deltaX = (float) 0.0;
	//			if (deltaY < NOISE)
	//				deltaY = (float) 0.0;
	//			if (deltaZ < NOISE)
	//				deltaZ = (float) 0.0;
	//			mLastX = x;
	//			mLastY = y;
	//			mLastZ = z;
	//			tvX.setText(Float.toString(deltaX));
	//			tvY.setText(Float.toString(deltaY));
	//			tvZ.setText(Float.toString(deltaZ));
	//			iv.setVisibility(View.VISIBLE);
	//			if (deltaX > deltaY) {
	//				iv.setImageResource(R.drawable.horizontal);
	//			} else if (deltaY > deltaX) {
	//				iv.setImageResource(R.drawable.vertical);
	//			} else {
	//				iv.setVisibility(View.INVISIBLE);
	//			}
	//		}
	//	}

	//Overridden lifecycle methods
	@Override
	protected void onResume() {
		super.onResume();

	}
	@Override
	protected void onPause() {
		super.onPause();

	}

}
