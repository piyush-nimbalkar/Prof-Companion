package com.example.diary;

import model.Event;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/* An activity which facilitates editing of the event information and
 * returning it back to the caller Activity
 */
public class EditEventActivity extends Activity {

	private final int RESULT_DELETED = 3;
	final Context context = this;
	private Event event;
	private int eventPosition;

	private EditText editTextEventType;
	private EditText editTextEventDay;
	private EditText editTextEventTime;
	private EditText editTextEventNote;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event);

		editTextEventType = (EditText) findViewById(R.id.editTextEditEventType);
		editTextEventDay = (EditText) findViewById(R.id.editTextEditEventDay);
		editTextEventTime = (EditText) findViewById(R.id.editTextEditEventTime);
		editTextEventNote = (EditText) findViewById(R.id.editTextEditEventNote);
		
		eventPosition = getIntent().getIntExtra("Position", -1);
		event = (Event) ((eventPosition == -1) ? new Event() : getIntent().getParcelableExtra("Event"));

		/* Fill out the event information on the screen from the received event object
		 */		
		editTextEventType.setText(event.getType());
		editTextEventDay.setText(event.getDay());
		editTextEventTime.setText(event.getTime());
		editTextEventNote.setText(event.getNote());
	}

	/* Return the updated event object back to the caller activity
	 */
	public void finish() {
		Intent data = new Intent();
		data.putExtra("Event", event);
		data.putExtra("Position", eventPosition);
		int resultCode = (event == null) ? RESULT_DELETED : RESULT_OK;
		setResult(resultCode, data);
		super.finish();
	}

	/* Menu options for the current screen on the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_event_menu, menu);
		return true;
	}

	/* Menu options on select callback for this screen. Currently, it only
	 * lets you edit and delete the event
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.save_event:
			event.setNote(editTextEventNote.getText().toString());
			event.setType(editTextEventType.getText().toString());
			event.setDay(editTextEventDay.getText().toString());
			event.setTime(editTextEventTime.getText().toString());
			finish();
			return true;
		case R.id.delete_event:
			event = null;
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
