/**
 * Copyright Sunit Katkar - 2008. 
 * http://sunitkatkar.blogspot.com/
 */
package com.examples.sendmail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/**
 * Android application to send an email programmatically. <br>
 * <br>
 * Note: You are FREE to use this application for your <b>personal use</b>. For
 * example, using this code as a learning or teaching aid is ok. If you can, please
 * give me some credit. <br><br>
 * Please <b>do
 * not</b> use it for commercial purposes like creating a product for
 * redistribution, etc. <br> 
 * <br>
 * I cannot guarantee that your Android OS powered phone will not crash by using
 * this example code. And, all the usual legal yadda yadda applies. :) <br>
 * <br>
 * If you come across a better way to write this code, please let me know.
 * 
 * @author Sunit Katkar ({@link http://www.vidyut.com/sunit})
 * @version 1.0
 * @since December 2008
 * 
 */
public class SendMail extends Activity {

	private EditText emailTo;
	private EditText emailSubject;
	private EditText emailBody;
	private Button btnSend;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Get handle to the text edit and button widgets
		emailTo = (EditText) findViewById(R.id.editTxtTo);
		emailSubject = (EditText) findViewById(R.id.editTxtSubject);
		emailBody = (EditText) findViewById(R.id.editTxtBody);
		btnSend = (Button) findViewById(R.id.btnEmailSend);

		// Attach a click listener to detect button click
		btnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.btnEmailSend) {
					sendEmail();
				}
			}
		});
	}

	/**
	 * Method to send email
	 */
	protected void sendEmail() {
		// Setup the recipient in a String array
		String[] mailto = { emailTo.getText().toString() };
		//String[] ccto = { "somecc@somedomain.com" };
		// Create a new Intent to send messages
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		// Add attributes to the intent
		sendIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
		//sendIntent.putExtra(Intent.EXTRA_CC, ccto);
		sendIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject.getText()
				.toString());
		sendIntent.putExtra(Intent.EXTRA_TEXT, emailBody.getText().toString());
		// sendIntent.setType("message/rfc822");
		sendIntent.setType("text/plain");
		startActivity(Intent.createChooser(sendIntent, "MySendMail"));
	}
}