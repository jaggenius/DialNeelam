package com.jagan.dialneelam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class ListPropertyFragment extends SherlockFragment{
	View v;
	Spinner propertyType, city,location;
	String sPropertyType="", sCity="", sLocation="";
	RadioGroup sellRent;
	String sSellRent = "sell";
	Button list;
	EditText address;
	
	public ListPropertyFragment(){};
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_list_property, container, false);
		sellRent= (RadioGroup) v.findViewById(R.id.radioGroup_list_property_sell_rent);
		sellRent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				sSellRent = (checkedId==R.id.radio_list_property_rent)?"rent":"sell";
			}
		});
		propertyType = (Spinner) v.findViewById(R.id.spinner_property_type);
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
		location = (Spinner) v.findViewById(R.id.spinner_list_property_location);
		updateLocations(R.array.location_empty);
		city = (Spinner) v.findViewById(R.id.spinner_list_property_city);
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
		address = (EditText) v.findViewById(R.id.editText_list_property_address);
		list = (Button) v.findViewById(R.id.button_list_property_list);
		list.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validateAll()){
					showAToast("Your property is listed");
				}
			}
		});
		return v;
		
	}

	protected boolean validateAll() {
		// TODO Auto-generated method stub
		String landmark = address.getText().toString().trim();
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
		else if(landmark.length()==0||!landmark.matches(".*[a-zA-Z]+.*")){
			showErrorMessage("proper landmark/address to enter.");
			return false;
		}
		else
			return true;
	}

	private void showErrorMessage(String field) {
		// TODO Auto-generated method stub
		AlertDialog.Builder ad = new AlertDialog.Builder(getSherlockActivity());
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

	protected void showAToast(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(getSherlockActivity(), message, Toast.LENGTH_SHORT).show();
	}

	protected void updateLocations(int locationsId) {
		// TODO Auto-generated method stub
		location.setAdapter(ArrayAdapter.createFromResource(getSherlockActivity(), locationsId, android.R.layout.simple_spinner_dropdown_item));
		//Log.d(TAG1, "location changed");
	}

}
