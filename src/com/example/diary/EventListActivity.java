package com.example.diary;

import java.util.ArrayList;

import model.Event;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EventListActivity extends Activity implements OnItemClickListener {

	private final int REQUEST_CODE1 = 1;
	private Context context;
	private ArrayList<Event> events = new ArrayList<Event>();
	private ListView listview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);
		context = this;
		listview = (ListView) findViewById(R.id.listViewEvents);
		events = getIntent().getParcelableArrayListExtra("Events");

		final EventArrayAdaptor adapter = new EventArrayAdaptor(this, events);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Event event = (Event) parent.getItemAtPosition(position);
		Intent i = new Intent(context, EditEventActivity.class);
		i.putExtra("Event", event);
		i.putExtra("Position", position);
		startActivityForResult(i, REQUEST_CODE1);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK){
			Event event = (Event) data.getExtras().get("Event");
			int position = data.getIntExtra("Position", 0);
			events.remove(position);
			events.add(position, event);
			final EventArrayAdaptor adapter = new EventArrayAdaptor(this, events);
			listview.setAdapter(adapter);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void finish() {
		Intent data = new Intent();
		data.putParcelableArrayListExtra("Events", events);
		setResult(RESULT_OK, data);
		super.finish();
	}

}
