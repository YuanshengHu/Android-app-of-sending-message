package com.example.javahomework;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
public class enterfaceactivity extends Activity{
	private String address,pass;
	private messagedb db_helper;
	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_layout);
		db_helper=new messagedb(this, "bookstorem.db",null,1);
		Button login=(Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				db_helper.getReadableDatabase();
				address=((EditText)findViewById(R.id.accout)).getText().toString();
				pass=((EditText)findViewById(R.id.password)).getText().toString();
				System.out.println(address);
				System.out.println(pass);
				if((address.equals("123"))&&(pass.equals("123"))){
					Intent intent = new Intent(enterfaceactivity.this, SecondActivity.class);
					startActivity(intent);
				}
					
				
			}
		});
	}
    
}
