package com.example.sleepapp_v1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static final String PSQI_SCORE = "PSQIscore";
    View v; 
    
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//test file in internal storage 
		String filename = "outputStream_testFile";
		String string = "Hello world!";
		FileOutputStream outputStream;
		try {
		  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		  outputStream.write(string.getBytes());
		  outputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		//test 2
		View v = this.findViewById(android.R.id.content); 
		Context context = v.getContext(); 
		String str = context.getFilesDir().toString(); 
		Log.w("File Dir", str); 
		File newFile = new File(context.getFilesDir(), "getFilesDir_TestFile");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
			String contents = "hi bitches"; 
			writer.write(contents);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startSurvey(View view){
		//test storage
		View v = this.findViewById(android.R.id.content); 
		Context context = v.getContext(); 
		String str = context.getFilesDir().toString(); 
		Log.w("File Dir", str); 
		File file = new File(context.getFilesDir(), "getFilesDir_TestFile");
		
		Intent intent = new Intent(this, PSQIsurvey.class);
		startActivity(intent);
	}
	
	public void startSleepSensor(View view){
		Intent intent = new Intent(this, SleepSensorActivity.class);
		startActivity(intent);
	}
	public void viewData(View view){
		Intent intent = new Intent(this, DataView.class);
		startActivity(intent);
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    //use SharedPreferences to check to see if it's the first time the app is running 
	    //if so, prompt the user to take the PSQI test
	    //if not, display PSQI score in textView 
	    
	    Log.w("Resume", "Main Activity Resumed");
		//retrieves score from shared preferences
	    //Test to see if it's there 
//		SharedPreferences settings = getSharedPreferences(PSQI_SCORE, 0);
//		int psqiScore = settings.getInt(PSQI_SCORE, -1);
//		Log.w("Shared Preferences", "score:" + psqiScore); 
//		View view = getWindow().getDecorView().findViewById(android.R.id.content);
//		TextView psqiTextView = (TextView)view.findViewById(R.id.psqi_score);
//		psqiTextView.setText("PSQI Score: " + psqiScore);
	}

	@Override
	protected void onPause() {
	  super.onPause();
	}

}
