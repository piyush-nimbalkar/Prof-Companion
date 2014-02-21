package com.example.diary;

import java.util.ArrayList;

import model.Event;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class EventActivity extends Activity {

	private ArrayList<Event> events = new ArrayList<Event>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);

		events = getIntent().getParcelableArrayListExtra("Events");

		Toast.makeText(this, events.get(0).getName(), Toast.LENGTH_LONG).show();
	}

}
