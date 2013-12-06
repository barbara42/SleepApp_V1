package com.example.sleepapp_v1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;

public class SleepSensorActivity extends Activity {

	private SensorManager mSensorManager;
	private SensorEventListener mSensorListener; 
	  private Sensor mLight;
	  private Sensor mAccelerometer; 
	  private Sensor mTemperature; 
	  private boolean hasLight = false; 
	  private boolean hasAccel = false; 
	  private boolean hasTemp = false; 
	  
	  @Override
	  public final void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sleep_sensor);
	    
	    //check which sensors are available on the phone
	    
//	    PackageManager packageManager = this.getPackageManager();
//	    boolean hasLightSensor = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT);
//	    boolean hasAccelerometer = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);
//	    boolean hasTempSensor = packageManager.hasSystemFeature(PackageManager.FEATURE_);
	    
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    //check which sensors are available on the phone
	    mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	    mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
	    if(mLight != null) hasLight = true; 
	    if(mAccelerometer != null) hasAccel = true; 
	    if(mTemperature != null) hasTemp = true; 

	    mSensorListener = new SensorEventListener(){
	    	@Override
	    	public void onAccuracyChanged(Sensor arg0, int arg1){
	    	}
	    	@Override
	    	public void onSensorChanged(SensorEvent event){
	    		Sensor sensor = event.sensor; 
	    		if (sensor.getType() == Sensor.TYPE_ACCELEROMETER){
	    			float accel = event.values[0];
	    			Log.w("Accel Sensor", "Accel: " + accel);
	    		}else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
	    			float temp = event.values[0];
	    			Log.w("Temp Sensor", "temp: " + temp);
	    		}else if (sensor.getType() == Sensor.TYPE_LIGHT){
	    			float lux = event.values[0];
	    			Log.w("Light Sensor", "Light: " + lux);
	    		}
	    	}
	    };
	  }
	
	  @Override
	  protected void onResume() {
	    super.onResume();
	    Log.w("Resume", "Sleep Sensor Activity Resumed");
	    if (hasAccel) mSensorManager.registerListener(mSensorListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	    if (hasTemp) mSensorManager.registerListener(mSensorListener, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
	    if (hasLight) mSensorManager.registerListener(mSensorListener, mLight, SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    mSensorManager.unregisterListener(mSensorListener);
	  }
	}