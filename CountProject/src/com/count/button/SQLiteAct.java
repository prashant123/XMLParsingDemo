package com.count.button;


import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SQLiteAct extends Activity implements OnClickListener{
	
	EditText edtName, edtNum;
    Button btnSave, btnList, btnUpdate, btnDel,btnDelTable;
    ListView list;
	ArrayAdapter<String> adapter;
	ArrayList<String> names = new ArrayList<String>();
    SQLiteDatabase db;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sqlite);
		
		intilization();
		
		// db = openOrCreateDatabase("main_db", MODE_PRIVATE, null);
		// createdb();
		
		SQLiteOpenHelper helper = new DBHelper(getApplicationContext(), "main_db", null, 1);
		db = helper.getWritableDatabase();
		
		//loaddata();
	}
	
	private void createdb()
	{
		// TODO Auto-generated method stub
		
		db.execSQL("create table friends ( number integer primary key, name text )");
		
	}

	private void intilization()
	{
		
		edtName   = (EditText) findViewById(R.id.edtName);
		edtNum    = (EditText) findViewById(R.id.edtNum);
		
		btnSave   = (Button) findViewById(R.id.btnSave);
		btnList   = (Button) findViewById(R.id.btnlist);
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnDel    = (Button) findViewById(R.id.btnDel);
		btnDelTable = (Button) findViewById(R.id.btnDelTable);
		
		btnSave.setOnClickListener(new BtnSaveListener());
		btnUpdate.setOnClickListener(this);
		btnDel.setOnClickListener(new BtnDelListener());
	
		btnList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub22
				
				startActivity(new Intent(getApplicationContext(),FriendList.class));
				
			}
		});
		
		btnDelTable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				 db.execSQL("DROP TABLE IF EXISTS friends");
				maket("drop table");
			}
		});
		
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
//		 list.setAdapter(adapter);
		
	}
	
	
	public class BtnDelListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			String []args = new String[1];
			args[0] = edtNum.getText().toString();
			
			maket("Row Deleted :" +edtNum.getText().toString());
			
			db.delete("friends", " number = ? ",  args );
			
		}
		
	}
	
		
	
	
	public class BtnSaveListener implements OnClickListener
	
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			ContentValues values = new ContentValues();
			values.put("number", Integer.parseInt(edtNum.getText().toString()));
			values.put("name", edtName.getText().toString());
			
			long res = db.insert("friends", null, values);
			
			if( res != -1 ) {
				maket("Row Inserted");
			}
			else{
				maket("Row Can not be Inserted");
			
			}
		}

	
		
	}


	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String []args = new String[1];
		args[0] = edtNum.getText().toString();
		
		ContentValues values = new ContentValues();
		values.put("number", Integer.parseInt(edtNum.getText().toString()));
		values.put("name", edtName.getText().toString());
		int res = db.update("friends", values, " number = ? ", args);
		
		if( res != 0 ) {
			maket("Row Updated");
		}else{
			maket("Row Can not be Updated");
		}
	
	}
	
	
	
	
	void loaddata() {
		names.clear();
		
		 SQLiteOpenHelper helper =  new DBHelper(this, "main_db", null, 1);
	      SQLiteDatabase  db1 = helper.getReadableDatabase();
	        
		Cursor c = db1.query("friends", null , null , null , null	, null, "number asc " );
		
		//c = db.rawQuery("select * from emp where id = ? ", selectionArgs)
		while( c.moveToNext() ) {
			String str = "";
			str  = c.getString(1);
			str += c.getInt(0);
			names.add(str);
		}
		adapter.notifyDataSetChanged();
		
	}
	
	private void maket(String string) {
		// TODO Auto-generated method stub
		
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
		
	}
}
