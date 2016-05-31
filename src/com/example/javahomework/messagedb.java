package com.example.javahomework;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class messagedb extends SQLiteOpenHelper{
	public static final String CREATE_BOOK1 = "create table Message ("
			+ "id integer primary key,"
			+ "receiver text, "
			+ "content text, "
			+ "time text, "
			+ "deleted integer,"
			+ "flag integer)";
	public static final String CREATE_BOOK2 = "create table Draft ("
			+ "id integer primary key,"
			+ "receiver text, "
			+ "content text)";
	private Context mContext;
	public messagedb(Context context, String name, CursorFactory factory, int version){
		super(context,name,factory,version);
		mContext = context;
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_BOOK1);
		db.execSQL(CREATE_BOOK2);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int a, int b){
		
	}

}
