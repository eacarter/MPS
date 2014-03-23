package com.ITOxygen.mps.Adapter;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ITOxygen.MPSModels.ParkingModel;
import com.ITOxygen.mps.R;
import com.haarman.listviewanimations.itemmanipulation.ExpandableListItemAdapter;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class ParkingLotAdapter extends ExpandableListItemAdapter<ParkingModel>{

	private Context mContext; 

	@Override
	public View getContentView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;

		if(v == null){
			v = LayoutInflater.from(mContext).inflate(R.layout.parkingcontent, parent, false);
		}

		ImageButton tenPlus = (ImageButton) v.findViewById(R.id.tenPlus);
		ImageButton tenMinus = (ImageButton) v.findViewById(R.id.tenMinus);
		ImageButton zero = (ImageButton) v.findViewById(R.id.zero);
		TextView time = (TextView) v.findViewById(R.id.time);
		
		//time.setText(getItem(position).getUpdated().toLocaleString());
		
        long diff = (System.currentTimeMillis() - (getItem(position).getUpdated()).getTime());
        
        long days = (diff/86400000);
        long hours = (diff/3600000) % 24;
        long minutes = (diff/60000) % 60;
        
        
        String stringText;
        
        if(days > 0){
        	stringText = days + " days ago";
        }
        else if(hours > 0){
        	stringText = hours + " hours ago";
        }
        else if(minutes > 0){
        	stringText= minutes + " minutes ago";
        }
        else
        	stringText = " moments ago";
        
		time.setText(stringText);

		tenPlus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendSpaces(2, getItem(position));
			}

		});

		tenMinus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendSpaces(1, getItem(position));
			}
		});

		zero.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendSpaces(0, getItem(position));
			}
		});

		return v;
	}

	private void sendSpaces(final int i, final ParkingModel model) {

		ParseObject point = ParseObject.createWithoutData("ParkingLots", model.id);
		point.put("Spaces",i);
		point.saveInBackground(new SaveCallback(){

			@Override
			public void done(ParseException arg0) {
				if(arg0 == null){
					Toast.makeText(mContext, "Successfully Saved", Toast.LENGTH_LONG).show();
					//TODO: if chain of images based on states
					model.setState(i);
					notifyDataSetChanged();
				}
				else{
					Toast.makeText(mContext, "Failed to update database", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	@Override
	public View getTitleView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if(v == null){
			v = LayoutInflater.from(mContext).inflate(R.layout.parkingtitle, parent, false);
		}

		TextView title = (TextView) v.findViewById(R.id.title);
		ImageView state = (ImageView) v.findViewById(R.id.state);

		title.setText("lot " + getItem(position).getNumber());
		//state.setText(getItem(position).state+"");

		if (getItem(position).state == 2) {
			state.setImageResource(R.drawable.smalltenplus);
		}

		else if(getItem(position).state == 1){
			state.setImageResource(R.drawable.smalltenminus);
		}

		else if(getItem(position).state == 0){
			state.setImageResource(R.drawable.smallzero);
		}

		else
			state.setImageResource(R.drawable.ic_launcher);

		return v;
	}

	public ParkingLotAdapter(Context context, List<ParkingModel> items ){
		super(context, R.layout.activtiy_expandablelistitem_card, R.id.activity_expandablelistitem_card_title, R.id.activity_expandablelistitem_card_content, items);
		mContext =context;

	}
}
