package com.example.javahomework;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
public class SecondActivity extends Activity{
	private String address2,pass2;
	private String[] data= { "开始发送","已发短信","短信垃圾箱","短信草稿","星标短信" };
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondActivity.this,
        		android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){
        	@Override
        	public void onItemClick(AdapterView<?>parent, View view, int position, long id){
        		switch(position){
        		case 0 :{
        			Intent intent20 = new Intent(SecondActivity.this, sendactivity.class);
        			startActivity(intent20);
        		}break;
        		case 1 :{
        			Intent intent21 = new Intent(SecondActivity.this, checksent.class);
        			startActivity(intent21);
        		}break;
        		case 2 :{
        			Intent intent22 = new Intent(SecondActivity.this, alreadydelete.class);
        			startActivity(intent22);
        		}break;
        		case 3 :{
        			Intent intent23 = new Intent(SecondActivity.this, thedraft.class);
        			startActivity(intent23);
        		}break;
        		case 4 :{
        			Intent intent24 = new Intent(SecondActivity.this, mailwithstar.class);
        			startActivity(intent24);
        		}break;
        		}
        			
        	}
        	
        });
	}


}
