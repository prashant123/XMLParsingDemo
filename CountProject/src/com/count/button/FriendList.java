package com.count.button;

import java.util.ArrayList;



import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FriendList extends ListActivity{
	
	SQLiteDatabase db;
	ListView list;
	ArrayList<String> fList;
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		list = getListView();
		fList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fList);
		setListAdapter(adapter);
		

        SQLiteOpenHelper helper =  new DBHelper(this, "main_db", null, 1);
        db = helper.getReadableDatabase();
		
		populateData();
	}

	private void populateData() {
		// TODO Auto-generated method stub
		
		Cursor cur = db.query("friends", null, null, null, null, null, " number asc");
		while( cur.moveToNext() )
		{
			String str = cur.getInt(0) + " : " + cur.getString(1);
			fList.add(str);
		}
		
		adapter.notifyDataSetChanged();
		
	}

}
