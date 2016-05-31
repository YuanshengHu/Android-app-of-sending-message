package com.example.javahomework;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
public class thedraft extends Activity{
	private messagedb dbhelper;
	private List<draft> messagelist = new ArrayList<draft>();
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checksentl);
		dbhelper=new messagedb(this, "bookstore.db",null,1);
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		Cursor cursor = db.query("Draft",null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				draft me = new draft(cursor.getString(cursor.getColumnIndex("receiver")),cursor.getString(cursor.getColumnIndex("content")));
				messagelist.add(me);
			}while (cursor.moveToNext());
		}
		cursor.close();
		draftadapter adapter = new draftadapter (thedraft.this, R.layout.draftitem, messagelist);
		ListView listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){
        	@Override
        	public void onItemClick(AdapterView<?>parent, View view, int position, long id){
				final draft mes = messagelist.get(position);
				AlertDialog.Builder dialog = new AlertDialog.Builder(thedraft.this);
				dialog.setTitle("����");
				dialog.setMessage("����Դ����ݸ����ʲô���Ĳ�����");
				dialog.setIcon(R.drawable.ic_launcher);
				dialog.setPositiveButton("ɾ��", new DialogInterface.OnClickListener() {
				     
				     @Override
				     public void onClick(DialogInterface dialog, int which) {
				    	 SQLiteDatabase db = dbhelper.getWritableDatabase();
				    	 db.delete("Draft","receiver=? AND content=? ",new String[] {mes.getid(),mes.gettext()});
				     }
				    });
				dialog.setNegativeButton("����", new DialogInterface.OnClickListener() {
				     
				     @Override
				     public void onClick(DialogInterface dialog, int which) {
				    	 SQLiteDatabase db = dbhelper.getWritableDatabase();
                         String s1=mes.getid();
                         String s2=mes.gettext();
                         Intent intent24 = new Intent(thedraft.this, sendactivity.class);
             			 intent24.putExtra("the_id", s1);
             			 intent24.putExtra("the_text", s2);
             			 startActivity(intent24);

				     }
				    });
				dialog.setNeutralButton("ȡ��", new DialogInterface.OnClickListener() {
				     
				     @Override
				     public void onClick(DialogInterface dialog, int which) {
				      // TODO Auto-generated method stub
				     }
				    });
				  dialog.show();
			}
		});
	}

}
