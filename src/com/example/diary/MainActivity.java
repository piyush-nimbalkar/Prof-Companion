package com.example.diary;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button contactButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		contactButton = (Button) findViewById(R.id.contact_button);
		contactButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.contact_button:
			Toast.makeText(this, "Here is my contact!", Toast.LENGTH_LONG).show();
			break;
		case R.id.courses_button:
			Toast.makeText(this, "Here are the courses I taught!", Toast.LENGTH_LONG).show();
			break;
		case R.id.events_button:
			Toast.makeText(this, "Here are my events!", Toast.LENGTH_LONG).show();
			break;
		case R.id.news_button:
			Toast.makeText(this, "Here are my news!", Toast.LENGTH_LONG).show();
			break;
		}
	}

}
