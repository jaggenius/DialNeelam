package com.jagan.dialneelam;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Home extends Activity {
	private static final String TAG1 = "com.jagan.dialneelam.home";
	Spinner propertyType, city, location;
	String sPropertyType="", sCity="", sLocation="";
	Button search,more,exit;
	RadioGroup buyRent;
	String sBuyRent = "buy";
	int locationResourceId = R.array.locations_chennai;
	Intent i;
	private static final int SEARCH_PROPERTY=0,LIST_PROPERTY=1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		buyRent = (RadioGroup) findViewById(R.id.radioGroup_buy_sell);
		buyRent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				sBuyRent = (checkedId==R.id.radio_buy)?"buy":"rent";
			}
		});
		propertyType = (Spinner) findViewById(R.id.spinner_home_property1);
		propertyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View currView,
					int pos, long id) {
				// TODO Auto-generated method stub				
				sPropertyType = (pos!=0)?parent.getItemAtPosition(pos).toString():"";
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		/*if(savedInstanceState!=null){
			propPos = savedInstanceState.getInt("propPos");
			cityPos = savedInstanceState.getInt("cityPos");
			locationPos = savedInstanceState.getInt("locationPos");
		}*/
			
		location = (Spinner) findViewById(R.id.spinner_home_location);		
		updateLocations(R.array.location_empty);
		city = (Spinner) findViewById(R.id.spinner_home_city);
		//sCity = "";
		city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View currView,
					int pos, long id) {
				// TODO Auto-generated method stub
				switch(pos){
				case 1:
					//locationResourceId = R.array.locations_chennai;					
					updateLocations(R.array.locations_chennai);
					sCity = parent.getItemAtPosition(pos).toString();
					break;
				default:
					sCity="";
					updateLocations(R.array.location_empty);
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		Log.d(TAG1, "location res id"+locationResourceId);
		
		
		location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View currView,
					int pos, long id) {
				// TODO Auto-generated method stub
				sLocation = (pos!=0)?parent.getItemAtPosition(pos).toString():"";
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		exit = (Button) findViewById(R.id.button_home_exit);
		exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callFinish();
			}
		});
		search = (Button) findViewById(R.id.button_home_view_all);
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Create an intent for search results page and pass all the search arguments
				if(validateAll())
					goToAll(SEARCH_PROPERTY);
			}
		});
		more = (Button) findViewById(R.id.button_home_go_home);
		more.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Go to the prev search result page. If possible display list property page first.
				//i = new Intent(getBaseContext(), AllPages.class);
				goToAll(LIST_PROPERTY);
			}
		});
		
	}

	protected boolean validateAll() {
		// TODO Auto-generated method stub
		if(sPropertyType==""){
			showErrorMessage("Property Type");
			return false;
		}
		else if(sCity==""){
			showErrorMessage("City");
			return false;
		}
		else if (sLocation==""){
			showErrorMessage("Location");
			return false;
		}
		else
			return true;
	}

	private void showErrorMessage(String field) {
		// TODO Auto-generated method stub
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setMessage("Please choose a "+ field);
		ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		ad.show();
	}

	protected void goToAll(int navigationItem) {
		// TODO Auto-generated method stub
		i = new Intent(this, AllPages.class);
		i.putExtra("navigationItem", navigationItem);
		startActivity(i);		
	}

	protected void callFinish() {
		// TODO Auto-generated method stub
		finish();
	}

	protected void updateLocations(int locationsId) {
		// TODO Auto-generated method stub		
		//locationAdap.addAll(getResources().getStringArray(locationResourceId));
		location.setAdapter(ArrayAdapter.createFromResource(this, locationsId, android.R.layout.simple_spinner_dropdown_item));
		Log.d(TAG1, "location changed");
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

}
