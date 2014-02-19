package com.example.diary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactActivity extends Activity {

	//ImageButton editName;
	TextView textViewName;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		textViewName = (TextView) findViewById(R.id.textViewName);
		textViewName.setText("Testing Git");
	}


}
