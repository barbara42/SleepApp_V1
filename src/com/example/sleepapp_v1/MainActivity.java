package com.example.sleepapp_v1;

import android.os.Bundle;
import android.app.Activity;
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startSurvey(View view){
		Intent intent = new Intent(this, PSQIsurvey.class);
		startActivity(intent);
	}
	
	public void startSleepSensor(View view){
		Intent intent = new Intent(this, SleepSensorActivity.class);
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
		SharedPreferences settings = getSharedPreferences(PSQI_SCORE, 0);
		int psqiScore = settings.getInt(PSQI_SCORE, -1);
		Log.w("Shared Preferences", "score:" + psqiScore); 
		View view = getWindow().getDecorView().findViewById(android.R.id.content);
		TextView psqiTextView = (TextView)view.findViewById(R.id.psqi_score);
		psqiTextView.setText("PSQI Score: " + psqiScore);
	}

	@Override
	protected void onPause() {
	  super.onPause();
	}

}
