package com.count.button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CountProjectActivity extends Activity {
    /** Called when the activity is first created. */
	
	Button btnCount,btnDeCount,btnNotification,btnSave,btnSaveFile,btnloadFile;
	TextView txtCount;
	private int mCount = 0;
	private EditText edtString1,edtString2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnCount = (Button) findViewById(R.id.btnCount);
        txtCount = (TextView) findViewById(R.id.txtCount);
        btnDeCount = (Button) findViewById(R.id.btnDeCount);
        btnNotification = (Button) findViewById(R.id.btnNotification);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnloadFile = (Button) findViewById(R.id.btnloadFile);
        btnSaveFile =(Button) findViewById(R.id.btnSaveFile);
        edtString1 =  (EditText) findViewById(R.id.edtString1);
        edtString2 = (EditText) findViewById(R.id.edtString2);
        
        
        File root = getFilesDir();
        
        File dir1 = new File(root, "dir1");
        dir1.mkdir();
        
        File file1 = new File(dir1, "file1");
        
        try {
			
        	file1.createNewFile();
        	
        	FileOutputStream fout = new FileOutputStream(file1);
        	String str = edtString1.getText().toString()+" : "+edtString2.getText().toString();
        	
        	fout.write(str.getBytes());
        	fout.close();
        	
        	FileInputStream fin = new FileInputStream(file1);
        	byte[] arr = new byte[100];
        	
        	int count = fin.read(arr);
        	txtCount.setText(new String(arr, 0, count));
        	fin.close();
        	
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        
        File[] files = root.listFiles();
        for (File file : files) {
        	
        	mt(file.getName());
        	
        	if (file.isDirectory()) {
				
        		for (File f : file.listFiles()) {
					
        			mt(f.getName());
				}
			}
			
		}
        
        
        Intent intent = new Intent();
    	intent.setAction("TEST");
    	
    
    	sendBroadcast(intent);
    	
        btnCount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				mCount++;
				
				txtCount.setText("Button Count : "+mCount);
				
				
			}
		});
        
        btnDeCount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 mCount--;
				
				txtCount.setText("Button Count : "+mCount);
				
				
				
			}
		});
        
        
        btnNotification.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Notification notification = new Notification(R.drawable.icon,"this is ticker text",System.currentTimeMillis());
				
				Intent intent = new Intent(getApplicationContext(),NewAct.class);
				
				PendingIntent pintent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
				notification.setLatestEventInfo(getApplicationContext(), "This is Title", "This is MSG", pintent);
				
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				notification.flags |= Notification.FLAG_ONGOING_EVENT;
				NotificationManager nMger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				
				nMger.notify(1, notification);
				
				
			}
		});
        
        
       btnSave.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		SharedPreferences sPref = getSharedPreferences("filedata", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sPref.edit();
		editor.putString("string1", edtString1.getText().toString());
		editor.putString("string2", edtString2.getText().toString());
		editor.commit();
		startActivity(new Intent(getApplicationContext(),NewAct.class));
			
		}
	});
       
       
       
       btnSaveFile.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			try {
				FileOutputStream fout = openFileOutput("filedata1", Activity.MODE_WORLD_WRITEABLE);
				
				String data = edtString1.getText().toString()+" : "+ edtString2.getText().toString();
				
				fout.write(data.getBytes());
				fout.close();
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});
       
       
       
       btnloadFile.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			try {
				FileInputStream fin = openFileInput("filedata1");
				byte[] arr = new byte[100];				
				int count = fin.read(arr);
				
				txtCount.setText(new String(arr,0,count));
				fin.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});
       
       
       
       
    }

	private void mt(String string) {
		// TODO Auto-generated method stub
		
		Toast.makeText(getApplicationContext(),string , Toast.LENGTH_SHORT).show();
		
	}
    
    
}