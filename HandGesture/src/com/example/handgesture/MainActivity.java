package com.example.handgesture;

import android.util.Log;
import android.os.Bundle; 
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;
import android.view.WindowManager;
import android.view.ViewTreeObserver;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import android.text.method.ScrollingMovementMethod;
import edu.washington.cs.touchfreelibrary.sensors.CameraGestureSensor;
import edu.washington.cs.touchfreelibrary.sensors.ClickSensor;
import edu.washington.cs.touchfreelibrary.touchemulation.GestureScroller;


public class MainActivity extends Activity implements CameraGestureSensor.Listener {
		
	/** Sensor that detects gestures. Calls the appropriate 
	 *  functions when the motions are recognized. */ 
	private CameraGestureSensor mGestureSensor;
	
	private boolean mOpenCVInitiated;
	
 	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
		mGestureSensor = new CameraGestureSensor(this);
		mGestureSensor.addGestureListener(this);
		mOpenCVInitiated = false;		
						
		
	}
 
	 
	/** OpenCV library initialization. */
	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status) {
			switch (status) {
				case LoaderCallbackInterface.SUCCESS: {
					mOpenCVInitiated = true; 
					CameraGestureSensor.loadLibrary();
					mGestureSensor.start();
					 
				} break;
				default:
				{
					super.onManagerConnected(status);
				} break;
			}  
		}
	};
	
	/** Called when the activity is resumed. The gesture detector is initialized. */
	@Override
	public void onResume() {
		super.onResume();
		if(!mOpenCVInitiated)
			return; 
		mGestureSensor.start();
	}
	  
	/** Called when the activity is paused. The gesture detector is stopped
	 *  so that the camera is no longer working to recognize gestures. */
	@Override
	public void onPause() {
		super.onPause();
		if(!mOpenCVInitiated)
			return; 
		mGestureSensor.stop();
	} 
    


	@Override
	public void onGestureUp(CameraGestureSensor caller, long gestureLength) {
		// TODO Auto-generated method stub
	runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), 
						"Up Gesture",
						Toast.LENGTH_SHORT).show();
			} 
		}); 
	}

	@Override
	public void onGestureDown(CameraGestureSensor caller, long gestureLength) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), 
						"down Gesture",
						Toast.LENGTH_SHORT).show();
			} 
		}); 
		
	}

	@Override
	public void onGestureLeft(CameraGestureSensor caller, long gestureLength) {
		// TODO Auto-generated method stub
	/*	runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), 
						"left Gesture",
						Toast.LENGTH_SHORT).show();
			} 
		}); */
		
	}

	@Override
	public void onGestureRight(CameraGestureSensor caller, long gestureLength) {
		// TODO Auto-generated method stub
	/*	runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), 
						"Right Gesture",
						Toast.LENGTH_SHORT).show();
			} 
		}); */
		
	}
}
