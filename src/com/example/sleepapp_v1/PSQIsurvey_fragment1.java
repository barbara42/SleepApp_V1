package com.example.sleepapp_v1;

import java.io.Console;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class PSQIsurvey_fragment1 extends Fragment {
	private static Button bedtimeButton;
	private static Button awakeButton; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_psqisurvey_fragment1, container, false);
        
        //set button listener
        Button nextButton1 = (Button) v.findViewById(R.id.next_button1);
        //nextButton1.setOnClickListener(myHandler1);
        bedtimeButton = (Button)v.findViewById(R.id.bedtime_button);
        bedtimeButton.setOnClickListener(timeHandler);
        awakeButton = (Button)v.findViewById(R.id.awake_button);
        awakeButton.setOnClickListener(timeHandler);
		
		//fallasleep spinner
		Spinner fallasleepSpinner = (Spinner)v.findViewById(R.id.fallasleep_spinner);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.minutes, android.R.layout.simple_spinner_dropdown_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fallasleepSpinner.setAdapter(adapter2); 
		
		
		//totalhours spinner
		Spinner totalSpinner = (Spinner)v.findViewById(R.id.totalhours_spinner);
		ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.hours, android.R.layout.simple_spinner_dropdown_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		totalSpinner.setAdapter(adapter4); 
		
		//inflate layout 
		return v;
	}
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.psqisurvey_fragment1, menu);
//		return true;
//	}
	
	
	View.OnClickListener timeHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if( bedtimeButton.getId() == ((Button)v).getId() ){
				DialogFragment newFragment = new TimePickerFragment();
			    newFragment.show(getFragmentManager(), "timePicker");
		    }
			else if( awakeButton.getId() == ((Button)v).getId() ){
				DialogFragment newFragment = new TimePickerFragment2();
			    newFragment.show(getFragmentManager(), "timePicker");
		    }
		}
	};
	
	public static class TimePickerFragment extends DialogFragment
		implements TimePickerDialog.OnTimeSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			
			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
			DateFormat.is24HourFormat(getActivity()));
		}
	
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			//change text of button
			bedtimeButton.setText(hourOfDay + ":" + minute);
		}
	}
	public static class TimePickerFragment2 extends DialogFragment
		implements TimePickerDialog.OnTimeSetListener {
	
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			
			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
			DateFormat.is24HourFormat(getActivity()));
		}
	
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			//change text of button
			awakeButton.setText(hourOfDay + ":" + minute);
		}
	}
}


