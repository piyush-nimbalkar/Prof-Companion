package com.example.diary;

import model.Event;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends Activity implements OnClickListener{

	private final int REQUEST_CODE1 = 1;
	final Context context = this;

	private Event event;
	private int eventPosition;

	private TextView textViewEventType;
	private TextView textViewEventDayTime;
	private TextView textViewEventNote;

	private Button buttonEditEvent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);

		textViewEventType = (TextView) findViewById(R.id.textViewEventType);
		textViewEventDayTime = (TextView) findViewById(R.id.textViewEventDayTime);
		textViewEventNote = (TextView) findViewById(R.id.textViewEventNote);
		buttonEditEvent = (Button) findViewById(R.id.buttonEditEvent);
		
		event = getIntent().getParcelableExtra("Event");
		eventPosition = getIntent().getIntExtra("Position", 0);

		textViewEventType.setText(event.getType());
		textViewEventDayTime.setText(event.getDay() + "; " + event.getTime());
		textViewEventNote.setText(event.getNote());
		
		buttonEditEvent.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.buttonEditEvent:
			Intent i = new Intent(context, EditEventActivity.class);
			i.putExtra("Event", event);
			startActivityForResult(i, REQUEST_CODE1);
			break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK){
				event = (Event) data.getExtras().get("Event");

				textViewEventType.setText(event.getType());
				textViewEventDayTime.setText(event.getDay() + "; " + event.getTime());
				textViewEventNote.setText(event.getNote());
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Event", event);
		data.putExtra("Position", eventPosition);
		setResult(RESULT_OK, data);
		super.finish();
	}
}
