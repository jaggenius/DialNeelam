package com.jagan.dialneelam;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.widget.ArrayAdapter;



public class AllPages extends SherlockFragmentActivity implements OnNavigationListener  {

	private static final int SEARCH_PROPERTY=0,LIST_PROPERTY=1,BOOKMARKED=2,LISTED=3,POST_REQUIREMENT=4,REQUIREMENT_POSTED=5,PROFILE = 6;
	SherlockFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_pages);
		ActionBar actionBar = getSupportActionBar();
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(new ArrayAdapter<String>
		(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.navigationList)), this);
		Intent i = getIntent();
		switch(i.getIntExtra("navigationItem", LIST_PROPERTY)){
		case SEARCH_PROPERTY:
			actionBar.setSelectedNavigationItem(SEARCH_PROPERTY);
			break;		
		case LIST_PROPERTY:
			actionBar.setSelectedNavigationItem(LIST_PROPERTY);
			break;	
		case PROFILE:		
			actionBar.setSelectedNavigationItem(PROFILE);
			break;
		case BOOKMARKED:
			actionBar.setSelectedNavigationItem(BOOKMARKED);
			break;
		case LISTED:
			actionBar.setSelectedNavigationItem(LISTED);
			break;
		case POST_REQUIREMENT:
			actionBar.setSelectedNavigationItem(POST_REQUIREMENT);
			break;
		case REQUIREMENT_POSTED:
			actionBar.setSelectedNavigationItem(REQUIREMENT_POSTED);
			break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.activity_all_pages, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		switch(itemPosition){
		case PROFILE:
			fragment = new ProfileFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_all_pages_container, fragment).commit();
			return true;
		case LIST_PROPERTY:
			fragment = new ListPropertyFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_all_pages_container, fragment).commit();
			return true;
		default:
			return true;
		}
		
	}

}
