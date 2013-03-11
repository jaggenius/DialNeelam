package com.jagan.dialneelam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.actionbarsherlock.app.SherlockFragment;

public class ProfileFragment extends SherlockFragment {
	View v;
	EditText name,phoneNo,email;
	Button save;
	
	
	public ProfileFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		v = inflater.inflate(R.layout.fragment_profile, container, false);
		name = (EditText) v.findViewById(R.id.editText_fragment_profile_name);
		phoneNo = (EditText) v.findViewById(R.id.editText_fragment_profile_phoneNo);
		email = (EditText) v.findViewById(R.id.editText_fragment_profile_email);
		save= (Button) v.findViewById(R.id.button_fragment_profile_save);
		final String PROFILE_NAME= getResources().getString(R.string.profile_name);
		final String PROFILE_PHONE = getResources().getString(R.string.profile_phone);
		final String PROFILE_EMAIL = getResources().getString(R.string.profile_email);
		final SharedPreferences sp = getSherlockActivity().getSharedPreferences(getResources()
				.getString(R.string.preference_profile_details),Context.MODE_PRIVATE);
		String savedName = sp.getString(PROFILE_NAME, "");
		String savedPhone = sp.getString(PROFILE_PHONE, "");
		String savedEmail = sp.getString(PROFILE_EMAIL, "");
		if(savedName!="" && savedPhone!="" && savedEmail!=""){
			name.setText(savedName);
			phoneNo.setText(savedPhone);
			email.setText(savedEmail);
		}			
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validateAll()){					
					SharedPreferences.Editor spedit = sp.edit();
					spedit.putString(PROFILE_NAME, name.getText().toString().trim());
					spedit.putString(PROFILE_PHONE, phoneNo.getText().toString().trim());
					spedit.putString(PROFILE_EMAIL, email.getText().toString().trim());
					spedit.commit();
					showMsgToast("Details saved successfully");
				}
			}
		});
		return v;
		
	}

	protected void showMsgToast(String message) {
		// TODO Auto-generated method stub
		Toast.makeText(getSherlockActivity(), message, Toast.LENGTH_SHORT).show();
	}

	protected boolean validateAll() {
		String sName = name.getText().toString().trim();
		String sPhoneNo = phoneNo.getText().toString().trim();
		String sEmail = email.getText().toString().trim();
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(!(sName.replaceAll("\\s", "").matches("^[a-zA-Z]+$")) || sName.length()<3){
			showErrorAlert("Invalid name","Name can contain only Aplhabets and should have at least 3 letters");
			return false;
		}
		else if (!sPhoneNo.matches("[0-9]+") || sPhoneNo.length()!=10){
			showErrorAlert("Invalid Phone","Phone number should be numeric and should contain 10 digits");
			return false;
		}
		else if (!sEmail.matches(EMAIL_PATTERN) || sEmail.length()==0){
			showErrorAlert("Invalid email","Please enter valid email (ex:abc@xyz.com)");
			return false;
		}
		
		else
			return true;
		// TODO Auto-generated method stub
		
	}

	private void showErrorAlert(String title, String message) {
		// TODO Auto-generated method stub
		AlertDialog.Builder d = new AlertDialog.Builder(getSherlockActivity());
		d.setTitle(title);
		d.setMessage(message);
		d.setIcon(android.R.drawable.ic_dialog_alert);
		d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		d.show();
	}

}
