package com.example.sleepapp_v1;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class PSQIsurvey extends FragmentActivity {
	//to keep track of answers 
	ArrayList<String> psqiArrayList1 = new ArrayList<String>(); 
	ArrayList<String> psqiArrayList2 = new ArrayList<String>();
	ArrayList<String> psqiArrayList3 = new ArrayList<String>();
    public static final String PSQI_SCORE = "PSQIscore";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_psqisurvey);
		
		//adding fragment
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		PSQIsurvey_fragment1 fragment = new PSQIsurvey_fragment1();
		fragmentTransaction.add(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.psqisurvey, menu);
		return true;
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	}
	
	public void button1_onClick(View v){
		psqiArrayList1.clear();
		//gets the values of all the spinners and puts them in an array 
		View myView = (View)v.getParent();
		Button bedtimeButton = (Button)myView.findViewById(R.id.bedtime_button);
		String bedtimeString = bedtimeButton.getText().toString(); 
		psqiArrayList1.add(bedtimeString);
		
		Spinner mySpinner = (Spinner)myView.findViewById(R.id.fallasleep_spinner);
		String fallasleep = mySpinner.getSelectedItem().toString();
		psqiArrayList1.add(fallasleep);
		
		Button awakeButton = (Button)myView.findViewById(R.id.awake_button);
		String awakeString = awakeButton.getText().toString(); 
		psqiArrayList1.add(awakeString);
		
		mySpinner = (Spinner)myView.findViewById(R.id.totalhours_spinner);
		String totalhours = mySpinner.getSelectedItem().toString();
		psqiArrayList1.add(totalhours);
		
		Log.w("coolshit", psqiArrayList1.toString());
		
		//next fragment
		Fragment nextFragment = new PSQIsurvey_fragment2();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); 
		transaction.replace(R.id.fragment_container, nextFragment);
		transaction.addToBackStack(null);
		transaction.commit(); 
	}
	
	public void button2_onClick(View view){
		View v = (View)view.getParent(); 
		int psqiPart5 = 0; 
		
		psqiArrayList2.add(String.valueOf(getIndex(v, R.id.radiogroup_q1)));
		psqiPart5+= getIndex(v, R.id.radiogroup_q2);
		psqiPart5+= getIndex(v, R.id.radiogroup_q3);
		psqiPart5+= getIndex(v, R.id.radiogroup_q4);
		psqiPart5+= getIndex(v, R.id.radiogroup_q5);
		psqiPart5+= getIndex(v, R.id.radiogroup_q6);
		psqiPart5+= getIndex(v, R.id.radiogroup_q7);
		psqiPart5+= getIndex(v, R.id.radiogroup_q8);
		psqiPart5+= getIndex(v, R.id.radiogroup_q9);
		psqiArrayList2.add(String.valueOf(psqiPart5));
		
		Log.w("cool shit", String.valueOf(psqiPart5));
		
		//next fragment
		Fragment nextFragment = new PSQIsurvey_fragment3();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); 
		transaction.replace(R.id.fragment_container, nextFragment);
		transaction.addToBackStack(null);
		transaction.commit(); 
	}
	
	public void button3_onClick(View view){
		View v = (View)view.getParent(); 
		psqiArrayList3.add(String.valueOf(getIndex(v, R.id.radiogroup_q10)));
		psqiArrayList3.add(String.valueOf(getIndex(v, R.id.radiogroup_q11)));
		psqiArrayList3.add(String.valueOf(getIndex(v, R.id.radiogroup_q12)));
		psqiArrayList3.add(String.valueOf(getIndex(v, R.id.radiogroup_q13)));
		//Log.w("cool shit", psqiArrayList3.toString());
		int score = calculateScore(); 
		Log.w("SCORE", "score:" + score); 
		SharedPreferences settings = getSharedPreferences(PSQI_SCORE, 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putInt(PSQI_SCORE, score);
	    // Commit the edits!
	    editor.commit();
	    
	    //save to SD card with timestamp 

		finish();
	}
	
	public int getIndex(View v, int id){
		RadioGroup radioButtonGroup = (RadioGroup)v.findViewById(id);
		int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
		View radioButton = radioButtonGroup.findViewById(radioButtonID);
		return radioButtonGroup.indexOfChild(radioButton);
	}
	
	public int calculateScore(){
		//component 1 = psqiArrayList3.get(3)
		int component1 = Integer.valueOf(psqiArrayList3.get(3)); 
		//component 2 = psqiArrayList1.get(1) (convert from min to points) + psqiArrayList2.get(0) (convert again) 
		int score2 = Integer.valueOf(psqiArrayList1.get(1)); 
		if (score2 > 60) score2 = 3; 
		else if (score2 > 30) score2 = 2; 
		else if (score2 > 15) score2 = 1; 
		else score2 = 0; 
		int score5a = Integer.valueOf(psqiArrayList2.get(0)); 
		int component2a = score2 + score5a;
		int component2 = 0; 
		if (component2a >=5) component2 = 3; 
		if (component2a >=3) component2 = 2; 
		if (component2a >=1) component2 = 1; 
		//component 3 = psqiArrayList1.get(3) (convert) 
		int component3a = Integer.valueOf(psqiArrayList1.get(3)); 
		int component3 = 0; 
		if (component3a > 7) component3 = 0; 
		else if (component3a >=6) component3 = 1; 
		else if (component3a >=5) component3 = 2; 
		else component3 = 3; 
		//component 4 = psqiArrayList1.get(3) / (hours between psqiArrayList1.get(0) and psqiArrayList1.get(2) ) 
		int totalsleep = Integer.valueOf(psqiArrayList1.get(3));
		String t1 = psqiArrayList1.get(0); 
		String t2 = psqiArrayList1.get(2); 
		String min_t1 = t1.substring(t1.indexOf(":")+1); 
		String min_t2 = t2.substring(t2.indexOf(":")+1); 
		String hour_t1 = t1.substring(0, t1.indexOf(":")); 
		String hour_t2 = t2.substring(0, t2.indexOf(":"));
		int hours = 0; 
		if (Integer.valueOf(hour_t1) > 12)
			hours = 24 - Integer.valueOf(hour_t1) + Integer.valueOf(hour_t2);
		else if (Integer.valueOf(hour_t2) > Integer.valueOf(hour_t1) )
			hours = Integer.valueOf(hour_t2) - Integer.valueOf(hour_t1);
		else Log.w("PSQI Error", "check things with time"); 
		int min = Math.abs(Integer.valueOf(min_t2) - Integer.valueOf(min_t1));
		int totalBed = Math.round(hours + min/60); 
		double percent = totalsleep / totalBed; 
		int component4 = 0; 
		if (percent > 0.85) component4 = 0; 
		else if (percent > 0.75) component4 = 1; 
		else if (percent > 0.65) component4 = 2; 
		else component4 = 3; 
		//component 5 = psqiArrayList2.get(1) (convert) 
		int component5a = Integer.valueOf(psqiArrayList2.get(1));
		int component5 = 0; 
		if (component5a >=19) component5 = 3; 
		else if (component5a >=10) component5 = 2; 
		else if (component5a >= 1) component5 = 1; 
		else component5 = 0; 
		//component 6 = psqiArrayList3.get(0)
		int component6 = Integer.valueOf(psqiArrayList3.get(0)); 
		//component 7 = psqiArrayList3.get(1) + psqiArrayList3.get(2) (convert) 
		int component7a = Integer.valueOf(psqiArrayList3.get(1)) + Integer.valueOf(psqiArrayList3.get(2)); 
		int component7 = 0; 
		if (component7a >= 5) component7 = 3; 
		else if (component7a >= 3) component7 = 2; 
		else if (component7a >= 1) component7 = 1; 
		else component7 = 0; 
		int score = component1 + component2+ component3 + component4 + component5 + component6 + component7; 
		return score; 
	}
}
