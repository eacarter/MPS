package com.ITOxygen.mps.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ITOxygen.MPSModels.ParkingModel;
import com.ITOxygen.mps.R;
import com.haarman.listviewanimations.itemmanipulation.ExpandableListItemAdapter;

public class ParkingLotAdapter extends ExpandableListItemAdapter<ParkingModel>{

	private Context mContext; 

	@Override
	public View getContentView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getTitleView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		if(v == null){
			v = LayoutInflater.from(mContext).inflate(R.layout.parkingtitle, parent, false);
		}
		
		TextView title = (TextView) v.findViewById(R.id.title);
		TextView state = (TextView) v.findViewById(R.id.state);
		
		title.setText("lot " + getItem(position).getNumber());
		state.setText(getItem(position).state+"");

		return v;
	}

	public ParkingLotAdapter(Context context, List<ParkingModel> items ){
		super(context, R.layout.activtiy_expandablelistitem_card, R.id.activity_expandablelistitem_card_title, R.id.activity_expandablelistitem_card_content, items);
		mContext =context;

	}
}
