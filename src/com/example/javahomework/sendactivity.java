package com.example.javahomework;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
public class sendactivity extends Activity {
	private String to_id, theme, maincontent;
	private boolean fflag=false,result;
	private String the_account,the_pass;
	private messagedb dbhelper;
	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendactivity);
		dbhelper = new messagedb(this,"bookstore.db",null,1);
		Button send=(Button) findViewById(R.id.send);
		Button log=(Button) findViewById(R.id.log);
		Button refresh=(Button) findViewById(R.id.refresh);
		Intent intent = getIntent();
		if(intent!=null){
			String s1=intent.getStringExtra("the_id");
			String s2=intent.getStringExtra("the_text");
			EditText m =((EditText)findViewById(R.id.determine));
			m.setText(s1);
			EditText n =((EditText)findViewById(R.id.zhengwen));
			n.setText(s2);
		}
		send.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
////////////////////////////////////////////////////////////////////////////
				final ProgressDialog pd= new ProgressDialog(sendactivity.this);
				pd.setTitle("THE message is sending");
				pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 
				pd.setMessage("Waiting...");
				pd.setIcon(R.drawable.ic_launcher);
				pd.setCancelable(true);
				pd.show();
				final Handler msgHandle=new Handler(){
					 @Override
					 public void handleMessage(Message msg){
					 pd.dismiss();            
					 super.handleMessage(msg);
					 }
					};
				new Thread(new Runnable(){
					
				@Override
				public void run(){
					try {
				to_id= ((EditText)findViewById(R.id.determine)).getText().toString();
				maincontent= ((EditText)findViewById(R.id.zhengwen)).getText().toString();
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(to_id,null,maincontent,null,null);
				Thread.sleep(500);
				SQLiteDatabase db = dbhelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("receiver",to_id);
				values.put("content",maincontent);
				SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");       
				String    date    =    sDateFormat.format(new    java.util.Date());
				values.put("time",date);
				values.put("deleted",0);
				values.put("flag",0);
				db.insert("message", null, values);
				pd.dismiss();
					} catch (InterruptedException e) { 
						e.printStackTrace();  
					}
					Bundle bundle=new Bundle();
					 Message m=new Message();
					 m.setData(bundle);
					 msgHandle.sendMessage(m);

				}}).start();
      		    Toast.makeText(sendactivity.this, "send successful", Toast.LENGTH_SHORT).show();
        		fflag=false;
			}
		});
		log.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				final ProgressDialog pd= new ProgressDialog(sendactivity.this);
				pd.setTitle("THE draft is saving");
				pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 
				pd.setMessage("Waiting...");
				pd.setIcon(R.drawable.ic_launcher);
				pd.setCancelable(true);
				pd.show();
				final Handler msgHandle=new Handler(){
					 @Override
					 public void handleMessage(Message msg){
					 pd.dismiss();            
					 super.handleMessage(msg);
					 }
					};
				new Thread(new Runnable(){
					
				@Override
				public void run(){
					try {
				to_id= ((EditText)findViewById(R.id.determine)).getText().toString();
				maincontent= ((EditText)findViewById(R.id.zhengwen)).getText().toString();
				Thread.sleep(500);
				SQLiteDatabase db = dbhelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("receiver",to_id);
				values.put("content",maincontent);
				db.insert("draft", null, values);
				pd.dismiss();
					} catch (InterruptedException e) { 
						e.printStackTrace();  
					}
					Bundle bundle=new Bundle();
					 Message m=new Message();
					 m.setData(bundle);
					 msgHandle.sendMessage(m);

				}}).start();
      		    Toast.makeText(sendactivity.this, "save successful", Toast.LENGTH_SHORT).show();
        		fflag=false;
			}
		});
		refresh.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				EditText m =((EditText)findViewById(R.id.determine));
				m.setText("");
				EditText n =((EditText)findViewById(R.id.zhengwen));
				n.setText("");
			}
		});
	}

}
