package com.example.sleepapp_v1;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

public class PSQIsurvey_fragment2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_psqisurvey_fragment2, container, false);
		//set button listener
        Button nextButton2 = (Button) v.findViewById(R.id.next_button2);
        //nextButton2.setOnClickListener(myHandler2);
        
        //inflate layout
        return v; 
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.psqisurvey_fragment1, menu);
//		return true;
//	}

//	View.OnClickListener myHandler2 = new View.OnClickListener() {
//		@Override
//		public void onClick(View v) {
//			Fragment nextFragment = new PSQIsurvey_fragment3();
//			FragmentTransaction transaction = getFragmentManager().beginTransaction(); 
//			//replace whatever is in the fragment_container view with this fragment and add the transaction to the backstack 
//			transaction.replace(R.id.fragment_container, nextFragment);
//			transaction.addToBackStack(null);
//			//commit transaction
//			transaction.commit(); 
//		}
//	};
}
