package com.example.sleepapp_v1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.text.format.Time;
import android.util.Log;


public class CSVgenerator {
	private int startHr;
	private int startMin;
	private int endHr; 
	private int endMin; 
	private ArrayList<String> timestamp = new ArrayList<String>(); 
	private ArrayList<String> lightData = new ArrayList<String>(); 
	private ArrayList<String> accelData = new ArrayList<String>(); 
	private ArrayList<String> tempData = new ArrayList<String>(); 
	private String annot_str = ""; 
	
	//constructor
	//create with initial time stamp 
	public CSVgenerator(int myHr, int myMin){
		startHr = myHr; 
		startMin = myMin;		
	}
	
	//add time stamp
	public void addTime(int myTime){
		String mtime = String.valueOf(myTime); 
		timestamp.add(mtime); 
	}
	//add light data
	public void addLightData(double myLightData){
		String lux = String.valueOf(myLightData);
		lightData.add(lux); 
	}
	
	//add accel data
	public void addAccelData(double myAccelData){
		String accel = String.valueOf(myAccelData);
		accelData.add(accel); 
	}
	
	//add temp data 
	public void addTempData(double myTempData){
		String temp = String.valueOf(myTempData);
		tempData.add(temp); 
	}
	
	public File generateCSV(String filesDir, int myEndHr, int myEndMin) throws IOException{
		//first row only has start time and end time [startHr, startMin, endHr, endMin]
		//other rows have [timestamp, light, accel, temp]
		
		endHr = myEndHr; 
		endMin = myEndMin; 
		annot_str = String.valueOf(startHr) + "," + String.valueOf(startMin) + "," + String.valueOf(endHr) + "," + String.valueOf(endMin);
		int numDataPoints = timestamp.size(); 
		
		Log.w("File Path", filesDir); 
		Log.w("SensorData", "LightData: " + lightData.toString());
		Log.w("SensorData", "AccelData: " + accelData.toString());
		Log.w("SensorData", "TempData: " + tempData.toString());

		for (int i = 0; i < numDataPoints; i++){
			annot_str += "/n"; 
			annot_str += timestamp.get(i) + "," + lightData.get(i) + "," + accelData.get(i) + "," + tempData.get(i); 
		}
		
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		String fileName = "SleepData_" + String.valueOf(today.month) + "_" + String.valueOf(today.monthDay) + "_" + String.valueOf(today.year);   
		File newFile = new File(filesDir, fileName);
		BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
		
		writer.write(annot_str);
		writer.close(); 
		
		return  newFile; 
	}

}
