package com.count.button;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class NewAct extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		TextView text = new TextView(this);
		
		text.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		
		SharedPreferences sPrf = getSharedPreferences("filedata", Activity.MODE_PRIVATE);
		String str1 = sPrf.getString("string1","");
		String str2 = sPrf.getString("string2", "");
		
		text.setText("new ACT:  "+str1+" : "+str2);
		
		setContentView(text);
		
		
	}

}
