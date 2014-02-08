package com.example.sleepapp_v1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class DataView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_view, menu);
		return true;
	}
	
	//test button click 
	public void onClick (View v){
		getDataFileFromInternalStorage("hi");
	}
	
	public void getDataFileFromInternalStorage(String fileName){
		//get test file in data/data/com.example.sleepapp_v1/files/getFilesDir_TestFile
		Context context = getApplicationContext();
		//test file 
		fileName = "getFilesDir_TestFile";
		String myPath = context.getFilesDir().toString(); 
		
		File folder = new File(myPath);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        System.out.println("File " + listOfFiles[i].getName());
	      } else if (listOfFiles[i].isDirectory()) {
	        System.out.println("Directory " + listOfFiles[i].getName());
	      }
	    }
		
		//code?? 
		int ch;
		StringBuffer fileContent = new StringBuffer("");
		FileInputStream fis;
		try {
		    fis = context.openFileInput(fileName);
		    try {
		        while( (ch = fis.read()) != -1)
		            fileContent.append((char)ch);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}

		String data = new String(fileContent);
		Log.w("file data", data); 
	}
	
	
}
