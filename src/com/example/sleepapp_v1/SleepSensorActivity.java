package com.example.sleepapp_v1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
import android.view.View;

public class SleepSensorActivity extends Activity {

	
	  private SensorManager mSensorManager;
	  private SensorEventListener mSensorListener; 
	  private Sensor mLight;
	  private Sensor mAccelerometer; 
	  private Sensor mTemperature; 
	  private boolean hasLight = false; 
	  private boolean hasAccel = false; 
	  private boolean hasTemp = false; 
	  private double lightData = 0;
	  private double accelData = 0; 
	  private double tempData = 0;
	  
	  private final Calendar c = Calendar.getInstance(); 
	  private int startHour = c.get(Calendar.HOUR);
	  private int startMin = c.get(Calendar.MINUTE);
	  private final int counter = 0; 
	  private final CSVgenerator csv = new CSVgenerator(startHour, startMin); 

	  Timer timer; 
	    
	  @Override
	  public final void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sleep_sensor);
	    
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
	    			accelData = accel; 
	    			//save data
//	    			csv.addLightData(lightData);
//	    			csv.addAccelData(accelData);
//	    			csv.addTempData(tempData); 
//	    			Log.w("Accel Sensor", "Accel: " + accel);
	    		}else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
	    			float temp = event.values[0];
	    			tempData = temp;
	    			//save data
//	    			csv.addLightData(lightData);
//	    			csv.addAccelData(accelData);
//	    			csv.addTempData(tempData); 	    			
//	    			Log.w("Temp Sensor", "temp: " + temp);
	    		}else if (sensor.getType() == Sensor.TYPE_LIGHT){
	    			float lux = event.values[0];
	    			lightData = lux;
	    			//save data 
//	    			csv.addLightData(lightData);
//	    			csv.addAccelData(accelData);
//	    			csv.addTempData(tempData); 
//	    			Log.w("Light Sensor", "Light: " + lux);
	    		}
	    	}
	    };
	    
	   
	    //save light, accel, temp in 250ms intervals
	    timer = new Timer(); 
	    timer.schedule(new TimerTask(){
	    	@Override
	    	public void run(){
	    		Log.w("Data", "Time:" + lightData + " "+ accelData + " " + tempData);
	    		//ADD DATA TO THINGS 
	    		//test
	    		csv.addTime(1); 
	    		//csv.addTime(c.HOUR, c.MINUTE); 
	    		csv.addAccelData(accelData);
	    		csv.addLightData(lightData);
	    		csv.addTempData(tempData);
	    	}
	    }, 250, 2*1000);
	  }
	  
	  @Override
	  protected void onResume() {
	    super.onResume();
	    Log.w("Resume", "Sleep Sensor Activity Resumed");
	    //COMMENT OUT
	    if (hasAccel) mSensorManager.registerListener(mSensorListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	    if (hasTemp) mSensorManager.registerListener(mSensorListener, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
	    if (hasLight) mSensorManager.registerListener(mSensorListener, mLight, SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    //COMMENT OUT
	    mSensorManager.unregisterListener(mSensorListener);
	  }
	  
	  public void endSleep(View v) throws IOException{
		  Context context = v.getContext(); 
		  timer.cancel(); 
		  timer.purge(); 
		  mSensorManager.unregisterListener(mSensorListener);
		  csv.generateCSV(context.getFilesDir().getPath(), 1, 1);
		  finish(); 
	  }
	}