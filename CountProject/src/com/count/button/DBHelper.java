package com.count.button;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

		Context mContext;
		
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Toast.makeText(mContext, "onCreate", Toast.LENGTH_SHORT).show();
		try {
			db.execSQL("create table friends ( number integer primary key , name text )");
		}
		catch( Exception e ) {
			
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		Toast.makeText(mContext, "onUpgrade " + oldVersion +"  " + newVersion , Toast.LENGTH_SHORT).show();
		
		
	}

}
