package com.example.javahomework;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
public class messageadapter extends ArrayAdapter<message>{
	private int resourceId;
	public messageadapter(Context context, int textViewResourceId, List<message> objects){
		super(context,textViewResourceId,objects);
		resourceId = textViewResourceId;
	}
	@Override
	public View getView (int position, View convertView,ViewGroup parent){
		message me = getItem(position);
		View view=LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView id = (TextView)view.findViewById(R.id.id);
		TextView text = (TextView)view.findViewById(R.id.text);
		TextView date = (TextView)view.findViewById(R.id.date);
		id.setText(me.getid());
		text.setText(me.gettext());
		date.setText(me.getdate());
		return view;
	}

}
