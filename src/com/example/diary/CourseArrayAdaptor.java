package com.example.diary;

import java.util.List;

import model.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CourseArrayAdaptor extends ArrayAdapter<Course>{
	private final Context context;
	private final List<Course> values;

	public CourseArrayAdaptor(Context context, List<Course> values) {
		super(context, R.layout.course_row_layout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.course_row_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.textViewRowCourseTitle);
		textView.setText(values.get(position).getCourseNumber());
		return rowView;
	}

}
