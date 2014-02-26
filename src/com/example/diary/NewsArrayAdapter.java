package com.example.diary;

import java.util.List;

import model.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsArrayAdapter extends ArrayAdapter<News> {

	private final Context context;
	private final List<News> values;

	public NewsArrayAdapter(Context context, List<News> values) {
		super(context, R.layout.news_row_layout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.news_row_layout, parent, false);
		TextView textViewTitle = (TextView) rowView.findViewById(R.id.textViewRowTitle);
		TextView textViewKeyword = (TextView) rowView.findViewById(R.id.textViewRowKeyword);
		TextView textViewHighlights = (TextView) rowView.findViewById(R.id.textViewRowHighlights);
		textViewTitle.setText(values.get(position).getTitle());
		textViewKeyword.setText(values.get(position).getKeyword());
		textViewHighlights.setText(values.get(position).getHighlights());
		return rowView;
	}

}
