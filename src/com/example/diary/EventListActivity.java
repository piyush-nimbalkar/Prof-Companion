package com.example.diary;

import java.util.ArrayList;

import model.Event;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/* An activity in which you can see a list of all the event and returns
 * back the updated list of events to the caller activity
 */
public class EventListActivity extends Activity implements OnItemClickListener {

	private final int REQUEST_EDIT = 1;
	private final int REQUEST_ADD = 2;
	private final int RESULT_DELETED = 3;

	private Context context;
	private ArrayList<Event> events = new ArrayList<Event>();
	private ListView listview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);
		context = this;
		listview = (ListView) findViewById(R.id.listViewEvents);
		events = getIntent().getParcelableArrayListExtra("Events");

		/* Using an adaptor to show the list of events in a custom layout
		 */
		final EventArrayAdapter adapter = new EventArrayAdapter(this, events);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	/* On click of the item in the list, it will open an activity to edit the event
	 * and pass the current event to it with its position in the list
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Event event = (Event) parent.getItemAtPosition(position);
		Intent i = new Intent(context, EditEventActivity.class);
		i.putExtra("Event", event);
		i.putExtra("Position", position);
		startActivityForResult(i, REQUEST_EDIT);
	}

	/* Update the list of events depending on the results from the returned activities
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Event event = (Event) data.getExtras().get("Event");
			switch (requestCode) {
			case REQUEST_EDIT:
				/* In case of an edit request it replaces the original object with the updated
				 * object that was received from the edit activity
				 */
				int position = data.getIntExtra("Position", 0);
				events.remove(position);
				events.add(position, event);
				break;
			case REQUEST_ADD:
				/* In case of an add request it will just add the object at the end of the list
				 */
				events.add(event);
				break;
			}
		} else if (resultCode == RESULT_DELETED) {
			/* In case of a delete request it remove the object from its position
			 */
			int position = data.getIntExtra("Position", -1);
			if (requestCode == REQUEST_EDIT)
				events.remove(position);
		}
		final EventArrayAdapter adapter = new EventArrayAdapter(this, events);
		listview.setAdapter(adapter);
		super.onActivityResult(requestCode, resultCode, data);
	}

	/* Return the updated or new event object back to the caller activity
	 */
	public void finish() {
		Intent data = new Intent();
		data.putParcelableArrayListExtra("Events", events);
		setResult(RESULT_OK, data);
		super.finish();
	}

	/* Menu options for the current screen on the action bar.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.event_list_menu, menu);
		return true;
	}

	/* Menu options on select callback for this screen. Currently, it only
	 * lets you add an event
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_event:
			Intent i = new Intent(context, EditEventActivity.class);
			startActivityForResult(i, REQUEST_ADD);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
