package com.example.diary;

import model.Event;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditEventActivity extends Activity implements OnClickListener {

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

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonEditEventDone:
			event.setNote(editTextEventNote.getText().toString());
			event.setType(editTextEventType.getText().toString());
			event.setDay(editTextEventDay.getText().toString());
			event.setTime(editTextEventTime.getText().toString());
			finish();
			break;
		}
	}

	public void finish() {
		Intent data = new Intent();
		data.putExtra("Event", event);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
