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
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.view.LayoutInflater;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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

}
