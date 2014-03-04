package com.example.diary;

import java.util.List;

import model.Event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/* Custom Array Adaptor to show the list of events
 */
public class EventArrayAdapter extends ArrayAdapter<Event> {

	private final Context context;
	private final List<Event> values;

	public EventArrayAdapter(Context context, List<Event> values) {
		super(context, R.layout.event_row_layout, values);
		this.context = context;
		this.values = values;
	}

	/* Override the method to return a custom view which will fill out the event
	 * information from the passed events list to the adaptor
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View eventView = inflater.inflate(R.layout.event_row_layout, parent, false);
		TextView textViewEventTitle = (TextView) eventView.findViewById(R.id.textViewRowEventTitle);
		TextView textViewEventTime = (TextView) eventView.findViewById(R.id.textViewRowEventTime);
		TextView textViewEventType = (TextView) eventView.findViewById(R.id.textViewRowEventType);
		textViewEventTitle.setText(values.get(position).getNote());
		textViewEventTime.setText(values.get(position).getDay() + ", " + values.get(position).getTime());
		textViewEventType.setText(values.get(position).getType());
		return eventView;
	}

}
