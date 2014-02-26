package com.example.diary;

import model.Event;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditEventActivity extends Activity implements OnClickListener{

	
	private final int REQUEST_CODE1 = 1;
	final Context context = this;

	private Event event;

	private EditText editTextEventType;
	private EditText editTextEventDay;
	private EditText editTextEventTime;
	private EditText editTextEventNote;

	private Button buttonEditEventDone;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event);
		
		editTextEventType = (EditText) findViewById(R.id.editTextEditEventType);
		editTextEventDay = (EditText) findViewById(R.id.editTextEditEventDay);
		editTextEventTime = (EditText) findViewById(R.id.editTextEditEventTime);
		editTextEventNote = (EditText) findViewById(R.id.editTextEditEventNote);
		buttonEditEventDone = (Button) findViewById(R.id.buttonEditEventDone);
		
		event = getIntent().getParcelableExtra("Event");

		editTextEventType.setText(event.getType());
		editTextEventDay.setText(event.getDay());
		editTextEventTime.setText(event.getTime());
		editTextEventNote.setText(event.getNote());
		
		buttonEditEventDone.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		
		
	}

}
