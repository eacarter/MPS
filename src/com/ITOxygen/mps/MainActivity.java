package com.ITOxygen.mps;

import java.util.ArrayList;
import java.util.List;

import com.ITOxygen.MPSModels.ParkingModel;
import com.ITOxygen.mps.Adapter.ParkingLotAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.view.LayoutInflater;

public class MainActivity extends Activity {

//	SharedPreferences mPrefs;
//	final String welcomeScreenShownPref = "Welcome to MPS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		final Dialog dialog = new Dialog(this);
		dialog.setTitle("Welcome to MPS");
		dialog.setContentView(R.layout.dialog);
		Button btn = (Button)dialog.findViewById(R.id.dismiss);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// need to check state of check box
				
				// save to preferences
				
				// dismiss the dialog box
				dialog.dismiss();
			}
		});
		dialog.show();
		

//		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//		
//		Boolean welcomeScreenShown = mPrefs.getBoolean(welcomeScreenShownPref, false);
//		
//		if(!welcomeScreenShown){
//			String whatsNewTitle = getResources().getString(R.string.app_name);
//	        String whatsNewText = getResources().getString(R.string.app_name);
//	        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(whatsNewTitle).setMessage(whatsNewText).setPositiveButton(
//	                R.string.hello_world, new DialogInterface.OnClickListener() {
//	                    public void onClick(DialogInterface dialog, int which) {
//	                        dialog.dismiss();
//	                    }
//	                }).show();
//	        SharedPreferences.Editor editor = mPrefs.edit();
//	        editor.putBoolean(welcomeScreenShownPref, true);
//	        editor.commit();
//		}
		
		ListView List = (ListView) findViewById(R.id.ParkingLotList);
		
		Parse.initialize(getApplication(), DeveloperKey.AppID, DeveloperKey.ClientKey);
		
		// get parking lot model list
		
		ArrayList<ParkingModel> modelList = populate();
		
		ParkingLotAdapter pAdapter = new ParkingLotAdapter(this, modelList);
		
		SwingBottomInAnimationAdapter animation = new SwingBottomInAnimationAdapter(pAdapter);
		
		animation.setAbsListView(List);
		animation.setInitialDelayMillis(500);
		
		List.setAdapter(animation);
	}

	/*
	 * 
	 */
	private ArrayList<ParkingModel> populate() {
		ArrayList<ParkingModel> modelList = new ArrayList<ParkingModel>();
		ParseQuery<ParseObject> parkingQuery = ParseQuery.getQuery("ParkingLots");
		parkingQuery.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		List<ParseObject> list;
		try {
			list = parkingQuery.find();
			for(ParseObject po : list){
				modelList.add(new ParkingModel(po));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
		
		if(item.getItemId() == R.id.action_settings){
			Toast.makeText(this, "about", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, About.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.startActivity(intent);
			return true;
		}
		
		return false;
	}

}
