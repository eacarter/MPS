package com.ITOxygen.mps;

import java.util.ArrayList;

import com.ITOxygen.MPSModels.ParkingModel;
import com.parse.Parse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView List = (ListView) R.layout.findViewById(R.id.ParkingLotList);
		
		Parse.initialize(getApplication(), DeveloperKey.AppID, DeveloperKey.ClientKey);
		
		// get parking lot model list
		
		ArrayList<ParkingModel> modelList = populate();
		
		
	}

	private ArrayList<ParkingModel> populate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
