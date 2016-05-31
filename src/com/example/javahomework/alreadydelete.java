package com.example.javahomework;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
public class alreadydelete extends Activity{
	private messagedb dbhelper;
	private List<message> messagelist = new ArrayList<message>();
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checksentl);
		dbhelper=new messagedb(this, "bookstore.db",null,1);
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		Cursor cursor = db.query("Message",null, "deleted=?", new String[]{"1"}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				message me = new message(cursor.getString(cursor.getColumnIndex("receiver")),cursor.getString(cursor.getColumnIndex("content")),cursor.getString(cursor.getColumnIndex("time")));
				messagelist.add(me);
			}while (cursor.moveToNext());
		}
		cursor.close();
		messageadapter adapter = new messageadapter (alreadydelete.this, R.layout.messageitem, messagelist);
		ListView listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){
        	@Override
        	public void onItemClick(AdapterView<?>parent, View view, int position, long id){
				final message mes = messagelist.get(position);
				AlertDialog.Builder dialog = new AlertDialog.Builder(alreadydelete.this);
				dialog.setTitle("操作");
				dialog.setMessage("您想对此条已删短信进行什么样的操作？");
				dialog.setIcon(R.drawable.ic_launcher);
				dialog.setPositiveButton("彻删", new DialogInterface.OnClickListener() {
				     
				     @Override
				     public void onClick(DialogInterface dialog, int which) {
				    	 SQLiteDatabase db = dbhelper.getWritableDatabase();
				    	 db.delete("Message","time=?",new String[] {mes.getdate()});
				     }
				    });
				dialog.setNegativeButton("还原", new DialogInterface.OnClickListener() {
				     
				     @Override
				     public void onClick(DialogInterface dialog, int which) {
				    	 SQLiteDatabase db = dbhelper.getWritableDatabase();
				    	 ContentValues values = new ContentValues();
				    	 values.put("deleted", 0);
				    	 db.update("Message", values, "time=?",new String[] {mes.getdate()});
				     }
				    });
				dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
				     
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
