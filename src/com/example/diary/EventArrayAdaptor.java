package com.example.diary;

import java.util.List;

import model.Event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventArrayAdaptor extends ArrayAdapter<Event>{
	private final Context context;
	private final List<Event> values;

	public EventArrayAdaptor(Context context, List<Event> values) {
		super(context, R.layout.event_row_layout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View eventView = inflater.inflate(R.layout.event_row_layout, parent, false);
		TextView textViewEventTitle = (TextView) eventView.findViewById(R.id.textViewRowEventTitle);
		TextView textViewEventTime = (TextView) eventView.findViewById(R.id.textViewRowEventTime);
		textViewEventTitle.setText(values.get(position).getType());
		textViewEventTime.setText(values.get(position).getDay() + "; " + values.get(position).getTime());
		return eventView;
	}

}
