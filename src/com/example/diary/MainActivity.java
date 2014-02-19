package com.example.diary;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button contactButton;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;

		contactButton = (Button) findViewById(R.id.buttonContact);
		contactButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonContact:
			Toast.makeText(context, "Here is my contact!", Toast.LENGTH_LONG).show();
			Intent i = new Intent(context, ContactActivity.class);
			startActivity(i);
			break;
		case R.id.buttonCourses:
			Toast.makeText(context, "Here are the courses I taught!", Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonEvents:
			Toast.makeText(context, "Here are my events!", Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonNews:
			Toast.makeText(context, "Here are my news!", Toast.LENGTH_LONG).show();
			break;
		}
	}

}
