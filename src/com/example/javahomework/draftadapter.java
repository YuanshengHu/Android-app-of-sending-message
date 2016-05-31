package com.example.javahomework;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class draftadapter extends ArrayAdapter<draft>{
	private int resourceId;
	public draftadapter(Context context, int textViewResourceId, List<draft> objects){
		super(context,textViewResourceId,objects);
		resourceId = textViewResourceId;
	}
	@Override
	public View getView (int position, View convertView,ViewGroup parent){
		draft me = getItem(position);
		View view=LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView id = (TextView)view.findViewById(R.id.id);
		TextView text = (TextView)view.findViewById(R.id.text);
		id.setText(me.getid());
		text.setText(me.gettext());
		return view;
	}
}